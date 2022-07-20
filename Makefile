.PHONY: check run test
check:
	./gradlew spotlessApply spotlessCheck spotlessKotlin detekt ktlintCheck --profile --daemon

run:
	./gradlew build

test:
	./gradlew test

.DEFAULT_GOAL := check
