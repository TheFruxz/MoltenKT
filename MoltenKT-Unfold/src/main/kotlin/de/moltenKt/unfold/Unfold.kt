@file:Suppress("unused") // TODO use kotlin context API to avoid 'useless' seeming object extensions

package de.moltenKt.unfold

import de.moltenKt.unfold.extension.asStyledComponent
import io.ktor.http.*
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentLike
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEventSource
import java.io.File
import java.net.URL
import java.nio.file.Path

fun text(build: TextComponent.Builder.() -> Unit) =
	Component.text().apply(build).build()

fun text(componentContent: String) =
	componentContent.asStyledComponent

fun spaceText() = Component.space()

fun emptyText() = Component.empty()

fun newline() = Component.newline()

fun MoltenContext<HoverEventSource<*>>.text(componentContent: String) =
	text { text(componentContent) }

fun MoltenContext<HoverEventSource<*>>.space() =
	text { text(" ") }

fun MoltenContext<HoverEventSource<*>>.empty() =
	text { text("") }

fun MoltenContext<HoverEventSource<*>>.newline() =
	text { text("\n") }

operator fun TextComponent.Builder.plus(component: ComponentLike) =
	append(component.asComponent())

fun TextComponent.Builder.text(componentContent: String) =
	this + componentContent.asStyledComponent

fun TextComponent.Builder.text(component: TextComponent) =
	this + component

fun TextComponent.Builder.text(componentContent: String, modify: TextComponent.Builder.() -> Unit) =
	this.append(componentContent.asStyledComponent.toBuilder().apply(modify).build())

fun TextComponent.Builder.text(component: TextComponent, modify: TextComponent.Builder.() -> Unit) =
	this.append(component.toBuilder().apply(modify).build())

fun TextComponent.Builder.hover(eventSource: MoltenContext<HoverEventSource<*>>.() -> HoverEventSource<*>) =
	hoverEvent(eventSource(MoltenContext.contextOf()))

fun TextComponent.Builder.click(click: MoltenContext<ClickEvent>.() -> ClickEvent) =
	clickEvent(click(MoltenContext.contextOf()))

fun MoltenContext<ClickEvent>.url(url: String) = ClickEvent.openUrl(url)

fun MoltenContext<ClickEvent>.url(url: URL) = ClickEvent.openUrl(url)

fun MoltenContext<ClickEvent>.url(url: Url) = ClickEvent.openUrl(url.toString())

fun MoltenContext<ClickEvent>.file(file: String) = ClickEvent.openFile(file)

fun MoltenContext<ClickEvent>.file(path: Path) = file("$path")

fun MoltenContext<ClickEvent>.file(file: File) = file(file.toPath())

fun MoltenContext<ClickEvent>.run(command: String) = ClickEvent.runCommand(command)

fun MoltenContext<ClickEvent>.suggest(command: String) = ClickEvent.suggestCommand(command)

fun MoltenContext<ClickEvent>.toPage(page: Int) = ClickEvent.changePage(page)

fun MoltenContext<ClickEvent>.toPage(page: String) = ClickEvent.changePage(page)

fun MoltenContext<ClickEvent>.copy(text: String) = ClickEvent.copyToClipboard(text)

fun MoltenContext<ClickEvent>.click(action: ClickEvent.Action, string: String) = ClickEvent.clickEvent(action, string)