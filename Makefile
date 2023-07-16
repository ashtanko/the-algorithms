.PHONY: default check test report treport lines md all

check:
	./gradlew spotlessApply spotlessCheck spotlessKotlin detekt ktlintCheck --profile --daemon

default:
	 make check && make md

md:
	truncate -s0 README.md && cat config/main.md >> README.md && cat build/reports/detekt/detekt.md >> README.md

all:
	make check && ./gradlew build && md

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

treport:
	make test & make report

lines:
	find . -name '*.kt' | xargs wc -l

.DEFAULT_GOAL := default
