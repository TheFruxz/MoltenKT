package de.jet.jvm.tool.container

class Container<T> {

	private var content: T? = null

	fun getContent(): T = content!!

	fun setContent(content: T) {
		this.content = content
	}

	fun isSet() = content != null

}