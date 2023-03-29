#Makefile

run-dist:
	./build/install/app/bin/app

build:
	gradle clean
	gradle build
	gradle test
	gradle checkstyleMain
	gradle checkstyleTest

.PHONY: build
