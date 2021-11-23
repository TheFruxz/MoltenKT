//[JET-Minecraft](../../../index.md)/[de.jet.minecraft.app](../index.md)/[JetApp](index.md)

# JetApp

[jvm]\
class [JetApp](index.md) : [App](../../de.jet.minecraft.structure.app/-app/index.md)

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [jvm]<br>object [Companion](-companion/index.md) : [AppCompanion](../../de.jet.minecraft.structure.app/-app-companion/index.md)&lt;[JetApp](index.md)&gt; |

## Functions

| Name | Summary |
|---|---|
| [add](../../de.jet.minecraft.structure.app/-app/add.md) | [jvm]<br>fun [add](../../de.jet.minecraft.structure.app/-app/add.md)(eventListener: [EventListener](../../de.jet.minecraft.structure.app.event/-event-listener/index.md))<br>fun [add](../../de.jet.minecraft.structure.app/-app/add.md)(component: [Component](../../de.jet.minecraft.structure.component/-component/index.md))<br>[jvm]<br>fun [add](../../de.jet.minecraft.structure.app/-app/add.md)(interchange: [Interchange](../../de.jet.minecraft.structure.command/-interchange/index.md))<br>Add Interchange |
| [bye](bye.md) | [jvm]<br>open override fun [bye](bye.md)() |
| [equals](../../de.jet.minecraft.structure.app/-app/index.md#-956268231%2FFunctions%2F-726029290) | [jvm]<br>operator override fun [equals](../../de.jet.minecraft.structure.app/-app/index.md#-956268231%2FFunctions%2F-726029290)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [getClassLoader](../../de.jet.minecraft.structure.app/-app/index.md#-1264005818%2FFunctions%2F-726029290) | [jvm]<br>@NotNull<br>fun [getClassLoader](../../de.jet.minecraft.structure.app/-app/index.md#-1264005818%2FFunctions%2F-726029290)(): @NotNull[ClassLoader](https://docs.oracle.com/javase/8/docs/api/java/lang/ClassLoader.html) |
| [getCommand](../../de.jet.minecraft.structure.app/-app/index.md#-1722902754%2FFunctions%2F-726029290) | [jvm]<br>@Nullable<br>open fun [getCommand](../../de.jet.minecraft.structure.app/-app/index.md#-1722902754%2FFunctions%2F-726029290)(@NotNullp0: @NotNull[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): @NullablePluginCommand? |
| [getConfig](../../de.jet.minecraft.structure.app/-app/index.md#969075559%2FFunctions%2F-726029290) | [jvm]<br>@NotNull<br>open override fun [getConfig](../../de.jet.minecraft.structure.app/-app/index.md#969075559%2FFunctions%2F-726029290)(): @NotNullFileConfiguration |
| [getDataFolder](../../de.jet.minecraft.structure.app/-app/index.md#-1341384527%2FFunctions%2F-726029290) | [jvm]<br>@NotNull<br>override fun [getDataFolder](../../de.jet.minecraft.structure.app/-app/index.md#-1341384527%2FFunctions%2F-726029290)(): @NotNull[File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html) |
| [getDefaultBiomeProvider](../../de.jet.minecraft.structure.app/-app/index.md#-1131500269%2FFunctions%2F-726029290) | [jvm]<br>@Nullable<br>open override fun [getDefaultBiomeProvider](../../de.jet.minecraft.structure.app/-app/index.md#-1131500269%2FFunctions%2F-726029290)(@NotNullp0: @NotNull[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Nullablep1: @Nullable[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?): @NullableBiomeProvider? |
| [getDefaultWorldGenerator](../../de.jet.minecraft.structure.app/-app/index.md#1284217961%2FFunctions%2F-726029290) | [jvm]<br>@Nullable<br>open override fun [getDefaultWorldGenerator](../../de.jet.minecraft.structure.app/-app/index.md#1284217961%2FFunctions%2F-726029290)(@NotNullp0: @NotNull[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @Nullablep1: @Nullable[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?): @NullableChunkGenerator? |
| [getDescription](../../de.jet.minecraft.structure.app/-app/index.md#-1721867755%2FFunctions%2F-726029290) | [jvm]<br>@NotNull<br>override fun [getDescription](../../de.jet.minecraft.structure.app/-app/index.md#-1721867755%2FFunctions%2F-726029290)(): @NotNullPluginDescriptionFile |
| [getFile](../../de.jet.minecraft.structure.app/-app/index.md#1854065005%2FFunctions%2F-726029290) | [jvm]<br>@NotNull<br>open fun [getFile](../../de.jet.minecraft.structure.app/-app/index.md#1854065005%2FFunctions%2F-726029290)(): @NotNull[File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html) |
| [getLog4JLogger](../../de.jet.minecraft.structure.app/-app/index.md#-360014269%2FFunctions%2F-726029290) | [jvm]<br>@NotNull<br>open fun [getLog4JLogger](../../de.jet.minecraft.structure.app/-app/index.md#-360014269%2FFunctions%2F-726029290)(): @NotNullLogger |
| [getLogger](../../de.jet.minecraft.structure.app/-app/index.md#-190444327%2FFunctions%2F-726029290) | [jvm]<br>@NotNull<br>open override fun [getLogger](../../de.jet.minecraft.structure.app/-app/index.md#-190444327%2FFunctions%2F-726029290)(): @NotNull[Logger](https://docs.oracle.com/javase/8/docs/api/java/util/logging/Logger.html) |
| [getName](../../de.jet.minecraft.structure.app/-app/index.md#-1617404175%2FFunctions%2F-726029290) | [jvm]<br>@NotNull<br>override fun [getName](../../de.jet.minecraft.structure.app/-app/index.md#-1617404175%2FFunctions%2F-726029290)(): @NotNull[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [getPluginLoader](../../de.jet.minecraft.structure.app/-app/index.md#1789185283%2FFunctions%2F-726029290) | [jvm]<br>@NotNull<br>override fun [getPluginLoader](../../de.jet.minecraft.structure.app/-app/index.md#1789185283%2FFunctions%2F-726029290)(): @NotNullPluginLoader |
| [getResource](../../de.jet.minecraft.structure.app/-app/index.md#1780025289%2FFunctions%2F-726029290) | [jvm]<br>@Nullable<br>open override fun [getResource](../../de.jet.minecraft.structure.app/-app/index.md#1780025289%2FFunctions%2F-726029290)(@NotNullp0: @NotNull[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): @Nullable[InputStream](https://docs.oracle.com/javase/8/docs/api/java/io/InputStream.html)? |
| [getServer](../../de.jet.minecraft.structure.app/-app/index.md#1660097158%2FFunctions%2F-726029290) | [jvm]<br>@NotNull<br>override fun [getServer](../../de.jet.minecraft.structure.app/-app/index.md#1660097158%2FFunctions%2F-726029290)(): @NotNullServer |
| [getSLF4JLogger](../../de.jet.minecraft.structure.app/-app/index.md#663398778%2FFunctions%2F-726029290) | [jvm]<br>@NotNull<br>open fun [getSLF4JLogger](../../de.jet.minecraft.structure.app/-app/index.md#663398778%2FFunctions%2F-726029290)(): @NotNullLogger |
| [getTextResource](../../de.jet.minecraft.structure.app/-app/index.md#1065257046%2FFunctions%2F-726029290) | [jvm]<br>@Nullable<br>fun [getTextResource](../../de.jet.minecraft.structure.app/-app/index.md#1065257046%2FFunctions%2F-726029290)(@NotNullp0: @NotNull[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): @Nullable[Reader](https://docs.oracle.com/javase/8/docs/api/java/io/Reader.html)? |
| [hashCode](../../de.jet.minecraft.structure.app/-app/index.md#-1047022707%2FFunctions%2F-726029290) | [jvm]<br>override fun [hashCode](../../de.jet.minecraft.structure.app/-app/index.md#-1047022707%2FFunctions%2F-726029290)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [hello](hello.md) | [jvm]<br>open override fun [hello](hello.md)() |
| [init](../../de.jet.minecraft.structure.app/-app/index.md#263625417%2FFunctions%2F-726029290) | [jvm]<br>fun [init](../../de.jet.minecraft.structure.app/-app/index.md#263625417%2FFunctions%2F-726029290)(@NotNullp0: @NotNullPluginLoader, @NotNullp1: @NotNullServer, @NotNullp2: @NotNullPluginDescriptionFile, @NotNullp3: @NotNull[File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html), @NotNullp4: @NotNull[File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html), @NotNullp5: @NotNull[ClassLoader](https://docs.oracle.com/javase/8/docs/api/java/lang/ClassLoader.html)) |
| [isEnabled](../../de.jet.minecraft.structure.app/-app/index.md#-655197240%2FFunctions%2F-726029290) | [jvm]<br>override fun [isEnabled](../../de.jet.minecraft.structure.app/-app/index.md#-655197240%2FFunctions%2F-726029290)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isNaggable](../../de.jet.minecraft.structure.app/-app/index.md#-1116561404%2FFunctions%2F-726029290) | [jvm]<br>override fun [isNaggable](../../de.jet.minecraft.structure.app/-app/index.md#-1116561404%2FFunctions%2F-726029290)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onCommand](../../de.jet.minecraft.structure.app/-app/index.md#1148868763%2FFunctions%2F-726029290) | [jvm]<br>open override fun [onCommand](../../de.jet.minecraft.structure.app/-app/index.md#1148868763%2FFunctions%2F-726029290)(@NotNullp0: @NotNullCommandSender, @NotNullp1: @NotNullCommand, @NotNullp2: @NotNull[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @NotNullp3: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;@NotNull[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [onDisable](../../de.jet.minecraft.structure.app/-app/on-disable.md) | [jvm]<br>open override fun [onDisable](../../de.jet.minecraft.structure.app/-app/on-disable.md)() |
| [onEnable](../../de.jet.minecraft.structure.app/-app/on-enable.md) | [jvm]<br>open override fun [onEnable](../../de.jet.minecraft.structure.app/-app/on-enable.md)() |
| [onLoad](../../de.jet.minecraft.structure.app/-app/on-load.md) | [jvm]<br>open override fun [onLoad](../../de.jet.minecraft.structure.app/-app/on-load.md)() |
| [onTabComplete](../../de.jet.minecraft.structure.app/-app/index.md#193072766%2FFunctions%2F-726029290) | [jvm]<br>@Nullable<br>open override fun [onTabComplete](../../de.jet.minecraft.structure.app/-app/index.md#193072766%2FFunctions%2F-726029290)(@NotNullp0: @NotNullCommandSender, @NotNullp1: @NotNullCommand, @NotNullp2: @NotNull[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), @NotNullp3: [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;@NotNull[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;): @Nullable[MutableList](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-mutable-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;? |
| [register](register.md) | [jvm]<br>open override fun [register](register.md)()<br>fun [register](../../de.jet.minecraft.structure.app/-app/register.md)(service: [Service](../../de.jet.minecraft.structure.service/-service/index.md))<br>fun [register](../../de.jet.minecraft.structure.app/-app/register.md)(serializable: [Class](https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html)&lt;out ConfigurationSerializable&gt;)<br>fun [register](../../de.jet.minecraft.structure.app/-app/register.md)(serializable: [KClass](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)&lt;out ConfigurationSerializable&gt;) |
| [reloadConfig](../../de.jet.minecraft.structure.app/-app/index.md#-1959235648%2FFunctions%2F-726029290) | [jvm]<br>open override fun [reloadConfig](../../de.jet.minecraft.structure.app/-app/index.md#-1959235648%2FFunctions%2F-726029290)() |
| [remove](../../de.jet.minecraft.structure.app/-app/remove.md) | [jvm]<br>fun [remove](../../de.jet.minecraft.structure.app/-app/remove.md)(eventListener: [EventListener](../../de.jet.minecraft.structure.app.event/-event-listener/index.md)) |
| [replace](../../de.jet.minecraft.structure.app/-app/replace.md) | [jvm]<br>fun [replace](../../de.jet.minecraft.structure.app/-app/replace.md)(identity: [Identity](../../../../JET-Native/-j-e-t--native/de.jet.library.tool.smart.identification/-identity/index.md)&lt;[Interchange](../../de.jet.minecraft.structure.command/-interchange/index.md)&gt;, environment: [Interchange](../../de.jet.minecraft.structure.command/-interchange/index.md))<br>Interchange must not be initialized before executing this! |
| [reset](../../de.jet.minecraft.structure.app/-app/reset.md) | [jvm]<br>fun [reset](../../de.jet.minecraft.structure.app/-app/reset.md)(service: [Service](../../de.jet.minecraft.structure.service/-service/index.md)) |
| [restart](../../de.jet.minecraft.structure.app/-app/restart.md) | [jvm]<br>fun [restart](../../de.jet.minecraft.structure.app/-app/restart.md)(service: [Service](../../de.jet.minecraft.structure.service/-service/index.md)) |
| [saveConfig](../../de.jet.minecraft.structure.app/-app/index.md#-1998581028%2FFunctions%2F-726029290) | [jvm]<br>open override fun [saveConfig](../../de.jet.minecraft.structure.app/-app/index.md#-1998581028%2FFunctions%2F-726029290)() |
| [saveDefaultConfig](../../de.jet.minecraft.structure.app/-app/index.md#-1312772871%2FFunctions%2F-726029290) | [jvm]<br>open override fun [saveDefaultConfig](../../de.jet.minecraft.structure.app/-app/index.md#-1312772871%2FFunctions%2F-726029290)() |
| [saveResource](../../de.jet.minecraft.structure.app/-app/index.md#-1107407536%2FFunctions%2F-726029290) | [jvm]<br>open override fun [saveResource](../../de.jet.minecraft.structure.app/-app/index.md#-1107407536%2FFunctions%2F-726029290)(@NotNullp0: @NotNull[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), p1: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |
| [setEnabled](../../de.jet.minecraft.structure.app/-app/index.md#496945089%2FFunctions%2F-726029290) | [jvm]<br>fun [setEnabled](../../de.jet.minecraft.structure.app/-app/index.md#496945089%2FFunctions%2F-726029290)(p0: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |
| [setNaggable](../../de.jet.minecraft.structure.app/-app/index.md#67101717%2FFunctions%2F-726029290) | [jvm]<br>override fun [setNaggable](../../de.jet.minecraft.structure.app/-app/index.md#67101717%2FFunctions%2F-726029290)(p0: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |
| [start](../../de.jet.minecraft.structure.app/-app/start.md) | [jvm]<br>fun [start](../../de.jet.minecraft.structure.app/-app/start.md)(componentIdentity: [Identity](../../../../JET-Native/-j-e-t--native/de.jet.library.tool.smart.identification/-identity/index.md)&lt;[Component](../../de.jet.minecraft.structure.component/-component/index.md)&gt;)<br>fun [start](../../de.jet.minecraft.structure.app/-app/start.md)(service: [Service](../../de.jet.minecraft.structure.service/-service/index.md)) |
| [stop](../../de.jet.minecraft.structure.app/-app/stop.md) | [jvm]<br>fun [stop](../../de.jet.minecraft.structure.app/-app/stop.md)(service: [Service](../../de.jet.minecraft.structure.service/-service/index.md))<br>fun [stop](../../de.jet.minecraft.structure.app/-app/stop.md)(componentIdentity: [Identity](../../../../JET-Native/-j-e-t--native/de.jet.library.tool.smart.identification/-identity/index.md)&lt;[Component](../../de.jet.minecraft.structure.component/-component/index.md)&gt;, unregisterComponent: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false) |
| [toString](../../de.jet.minecraft.structure.app/-app/index.md#1897086895%2FFunctions%2F-726029290) | [jvm]<br>@NotNull<br>open override fun [toString](../../de.jet.minecraft.structure.app/-app/index.md#1897086895%2FFunctions%2F-726029290)(): @NotNull[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [unregister](../../de.jet.minecraft.structure.app/-app/unregister.md) | [jvm]<br>fun [unregister](../../de.jet.minecraft.structure.app/-app/unregister.md)(componentIdentity: [Identity](../../../../JET-Native/-j-e-t--native/de.jet.library.tool.smart.identification/-identity/index.md)&lt;[Component](../../de.jet.minecraft.structure.component/-component/index.md)&gt;)<br>fun [unregister](../../de.jet.minecraft.structure.app/-app/unregister.md)(service: [Service](../../de.jet.minecraft.structure.service/-service/index.md))<br>fun [unregister](../../de.jet.minecraft.structure.app/-app/unregister.md)(serializable: [Class](https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html)&lt;out ConfigurationSerializable&gt;)<br>fun [unregister](../../de.jet.minecraft.structure.app/-app/unregister.md)(serializable: [KClass](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)&lt;out ConfigurationSerializable&gt;) |

## Properties

| Name | Summary |
|---|---|
| [appCache](app-cache.md) | [jvm]<br>open override val [appCache](app-cache.md): [JetCache](../-jet-cache/index.md)<br>The cache of the application |
| [appIdentity](app-identity.md) | [jvm]<br>open override val [appIdentity](app-identity.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [appLabel](app-label.md) | [jvm]<br>open override val [appLabel](app-label.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>This value defines the display-name of this app, which will be displayed in app-lists and information with the app included. Can be duplicated, but try to avoid duplicated display names with other apps! |
| [appRegistrationFile](../../de.jet.minecraft.structure.app/-app/app-registration-file.md) | [jvm]<br>var [appRegistrationFile](../../de.jet.minecraft.structure.app/-app/app-registration-file.md): YamlConfiguration |
| [companion](companion.md) | [jvm]<br>open override val [companion](companion.md): [JetApp.Companion](-companion/index.md)<br>This value defines the Companion-Object of this class, which holds the instance variable *(lateinit recommended)* and other app-related special variables & functions. |
| [identity](../../de.jet.minecraft.structure.app/-app/identity.md) | [jvm]<br>open override val [identity](../../de.jet.minecraft.structure.app/-app/identity.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [identityObject](../../de.jet.minecraft.tool.timing.cooldown/-cooldown/index.md#-527806782%2FProperties%2F-726029290) | [jvm]<br>open val [identityObject](../../de.jet.minecraft.tool.timing.cooldown/-cooldown/index.md#-527806782%2FProperties%2F-726029290): [Identity](../../../../JET-Native/-j-e-t--native/de.jet.library.tool.smart.identification/-identity/index.md)&lt;[App](../../de.jet.minecraft.structure.app/-app/index.md)&gt; |
| [languageSpeaker](../../de.jet.minecraft.structure.app/-app/language-speaker.md) | [jvm]<br>val [languageSpeaker](../../de.jet.minecraft.structure.app/-app/language-speaker.md): [LanguageSpeaker](../../de.jet.minecraft.runtime.app/-language-speaker/index.md) |
| [log](../../de.jet.minecraft.structure.app/-app/log.md) | [jvm]<br>val [log](../../de.jet.minecraft.structure.app/-app/log.md): [Logger](https://docs.oracle.com/javase/8/docs/api/java/util/logging/Logger.html) |
| [logger](../../de.jet.minecraft.structure.app/-app/index.md#625757323%2FProperties%2F-726029290) | [jvm]<br>val [logger](../../de.jet.minecraft.structure.app/-app/index.md#625757323%2FProperties%2F-726029290): [Logger](https://docs.oracle.com/javase/8/docs/api/java/util/logging/Logger.html) |
| [runStatus](../../de.jet.minecraft.structure.app/-app/run-status.md) | [jvm]<br>var [runStatus](../../de.jet.minecraft.structure.app/-app/run-status.md): [RunStatus](../../de.jet.minecraft.runtime.app/-run-status/index.md)<br>The current status of app-runtime |