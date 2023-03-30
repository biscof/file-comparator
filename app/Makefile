#Makefile

prepare:
	gradle clean
	gradle installDist

build:
	gradle clean
	gradle build
	gradle test
	gradle checkstyleMain
	gradle checkstyleTest

report:
	gradle jacocoTestReport

.PHONY: build
