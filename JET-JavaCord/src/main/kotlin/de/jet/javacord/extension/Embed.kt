package de.jet.javacord.extension

/*

How embeds should work:

buildMessage / buildTransmission {
	buildEmbed {
		author(...) <- e.g. author(1283471293812L)
		color(color) <- e.g. Color.RED - but more colors supported, also hexadecimal colors
		description(...) <- e.g. description("Hello World!")
	}
	receiver(user) <- e.g. receiver(21311231412L) or receiver(channel), ...
}.send() <- or .send(channel) or .send(server) or .send(user) if the user is not specified

and other functions, without a set or get in front of the function
additional with some kotlin magic

 */