//[JET-Minecraft](../../index.md)/[de.jet.minecraft.extension.tasky](index.md)

# Package de.jet.minecraft.extension.tasky

## Functions

| Name | Summary |
|---|---|
| [async](async.md) | [jvm]<br>fun [async](async.md)(temporalAdvice: [TemporalAdvice](../de.jet.minecraft.tool.timing.tasky/-temporal-advice/index.md) = TemporalAdvice.instant(async = true), killAtError: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, vendor: [App](../de.jet.minecraft.structure.app/-app/index.md) = system, onStart: [Tasky](../de.jet.minecraft.tool.timing.tasky/-tasky/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}, onStop: [Tasky](../de.jet.minecraft.tool.timing.tasky/-tasky/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}, onCrash: [Tasky](../de.jet.minecraft.tool.timing.tasky/-tasky/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}, serviceVendor: [Identity](../../../JET-Native/-j-e-t--native/de.jet.library.tool.smart.identification/-identity/index.md)&lt;[Service](../de.jet.minecraft.structure.service/-service/index.md)&gt; = Identifiable.custom&lt;Service&gt;("dummy").identityObject, process: [Tasky](../de.jet.minecraft.tool.timing.tasky/-tasky/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [Tasky](../de.jet.minecraft.tool.timing.tasky/-tasky/index.md) |
| [sync](sync.md) | [jvm]<br>fun [sync](sync.md)(temporalAdvice: [TemporalAdvice](../de.jet.minecraft.tool.timing.tasky/-temporal-advice/index.md) = TemporalAdvice.instant(async = false), killAtError: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, vendor: [App](../de.jet.minecraft.structure.app/-app/index.md) = system, onStart: [Tasky](../de.jet.minecraft.tool.timing.tasky/-tasky/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}, onStop: [Tasky](../de.jet.minecraft.tool.timing.tasky/-tasky/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}, onCrash: [Tasky](../de.jet.minecraft.tool.timing.tasky/-tasky/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}, serviceVendor: [Identity](../../../JET-Native/-j-e-t--native/de.jet.library.tool.smart.identification/-identity/index.md)&lt;[Service](../de.jet.minecraft.structure.service/-service/index.md)&gt; = Identifiable.custom&lt;Service&gt;("dummy").identityObject, process: [Tasky](../de.jet.minecraft.tool.timing.tasky/-tasky/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [Tasky](../de.jet.minecraft.tool.timing.tasky/-tasky/index.md) |
| [task](task.md) | [jvm]<br>fun [task](task.md)(temporalAdvice: [TemporalAdvice](../de.jet.minecraft.tool.timing.tasky/-temporal-advice/index.md), killAtError: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, vendor: [App](../de.jet.minecraft.structure.app/-app/index.md) = system, onStart: [Tasky](../de.jet.minecraft.tool.timing.tasky/-tasky/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}, onStop: [Tasky](../de.jet.minecraft.tool.timing.tasky/-tasky/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}, onCrash: [Tasky](../de.jet.minecraft.tool.timing.tasky/-tasky/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}, serviceVendor: [Identity](../../../JET-Native/-j-e-t--native/de.jet.library.tool.smart.identification/-identity/index.md)&lt;[Service](../de.jet.minecraft.structure.service/-service/index.md)&gt; = Identifiable.custom&lt;Service&gt;("dummy").identityObject, process: [Tasky](../de.jet.minecraft.tool.timing.tasky/-tasky/index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [Tasky](../de.jet.minecraft.tool.timing.tasky/-tasky/index.md) |
| [wait](wait.md) | [jvm]<br>fun &lt;[T](wait.md)&gt; [T](wait.md).[wait](wait.md)(ticks: [Long](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html), code: [T](wait.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)): [T](wait.md) |