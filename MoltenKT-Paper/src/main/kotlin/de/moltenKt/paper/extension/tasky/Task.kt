package de.moltenKt.paper.extension.tasky

import de.moltenKt.core.extension.dump
import de.moltenKt.paper.extension.paper.createKey
import de.moltenKt.paper.extension.paper.createNamespacedKey
import de.moltenKt.paper.extension.system
import de.moltenKt.paper.structure.app.App
import de.moltenKt.paper.tool.timing.tasky.Tasky
import de.moltenKt.paper.tool.timing.tasky.TemporalAdvice
import de.moltenKt.paper.tool.timing.tasky.TemporalAdvice.Companion
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.future.await
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import net.kyori.adventure.key.Key
import java.util.concurrent.CompletableFuture
import kotlin.time.Duration

/**
 * Exceptions are caught!
 */
fun task(
	temporalAdvice: TemporalAdvice,
	killAtError: Boolean = true,
	vendor: App = system,
	onStart: Tasky.() -> Unit = {},
	onStop: Tasky.() -> Unit = {},
	onCrash: Tasky.() -> Unit = {},
	serviceVendor: Key = vendor.createKey("undefined"),
	process: Tasky.() -> Unit,
) = Tasky.task(vendor, temporalAdvice, killAtError, onStart, onStop, onCrash, serviceVendor, process)

/**
 * This function creates a sync task using the [Tasky] system.
 * Internally a [CompletableFuture] is used to create the delayed result.
 * Because the [CompletableFuture.await] function uses the coroutine suspend
 * feature, this function also utilizes the suspend function feature, so the
 * result can be easily managed into your existing MoltenKT App.
 * @param delay The delay before the process is executed; default none.
 * @param process The process to be executed.
 * @return The direct result of the process.
 * @author Fruxz
 * @since 1.0
 */
suspend fun <T> asSync(delay: Duration = Duration.ZERO, process: () -> T): T {
	val output = CompletableFuture<T>()

	task(when {
		delay.isPositive() -> TemporalAdvice.delayed(delay, async = false)
		else -> TemporalAdvice.instant(async = false)
	}) {
		output.complete(process())
	}

	return output.await()
}

/**
 * This function creates a sync task using the [Tasky] system.
 * Internally a [CompletableFuture] is used to create the delayed result.
 * Instead of the [asSync] function, this is not a suspending function, so
 * this function can be used anywhere you want!
 * @param delay The delay before the process is executed; default none.
 * @param cycleDuration The delay before the process is executed again; default none.
 * @param process The process to be executed
 * @author Fruxz
 * @since 1.0
 */
fun <T> doSync(delay: Duration = Duration.ZERO, cycleDuration: Duration = Duration.ZERO, process: () -> T): T {
	val output = CompletableFuture<T>()

	task(when {
		delay.isPositive() && !cycleDuration.isPositive() -> TemporalAdvice.delayed(delay, async = false)
		delay.isPositive() && cycleDuration.isPositive() -> TemporalAdvice.ticking(delay, cycleDuration, async = false)
		else -> TemporalAdvice.instant(async = false)
	}) {
		output.complete(process())
	}

	return output.get()
}

/**
 * This function creates an async context using the KotlinX Coroutines Library.
 * Returns the result as a [Deferred] object, for multiple-line executions.
 * Utilizes the [CoroutineScope.async] function, to create a safe environment.
 * @param delay The delay before the process is executed; default none.
 * @param process The process to be executed.
 * @return The result of the process as a [Deferred] object.
 * @see CoroutineScope.async
 * @see Deferred
 * @see CoroutineScope
 * @author Fruxz
 * @since 1.0
 */
fun <T> asAsync(delay: Duration = Duration.ZERO, process: suspend (CoroutineScope) -> T): Deferred<T> = system.coroutineScope.async {
	if (delay.isPositive()) delay(delay)
	return@async process(system.coroutineScope)
}

/**
 * This function executes the [process] asynchronously via the [launch] function.
 * @param delay The delay before the process is executed; default none.
 * @param cycleDuration The delay before the process is executed again; default none.
 * @param process The process to be executed.
 * @return The job executing this code *take a look at Kotlin Coroutines*
 * @author Fruxz
 * @since 1.0
 */
fun doAsync(delay: Duration = Duration.ZERO, cycleDuration: Duration = Duration.ZERO, process: suspend (CoroutineScope) -> Unit) = launch {
	if (delay.isPositive()) delay(delay)

	if (cycleDuration.isPositive()) {
		while (it.isActive) {
			process.invoke(it)
		}
	} else
		process.invoke(it)

}

fun launch(
	vendor: App = system,
	process: suspend (CoroutineScope) -> Unit,
) = vendor.coroutineScope.launch(block = process)

suspend fun <T, O> T.wait(duration: Duration, code: suspend T.() -> O): O = asAsync(duration) {
	code(this@wait)
}.await()

fun <T, O> T.delayed(duration: Duration, code: suspend T.() -> O) = asAsync { wait(duration, code) }.dump()