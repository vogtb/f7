# ==================================================================================================
# The makefile for every buildable thing, and most development tasks inside this repo.
#
# All buildable things have a `build-X` target that builds the conceptual thing (eg: app, service,
# library), while the targets that make up that thing are named according to their literal
# target path.
# ==================================================================================================

PWD := $(shell pwd)
OS ?= darwin
ARCH ?= arm64
ENV ?= dev

YARNCMD := yarn
YARNINSTALL := $(YARNCMD) install

ESLINT := ./node_modules/eslint/bin/eslint.js . --ext=.ts,.tsx --fix
PRETTIER := ./node_modules/prettier/bin-prettier.js --write '**/(*.css|*.js|*.ts|*.jsx|*.tsx|*.md)'

.PHONY: all

# ==================================================================================================
# all
# ==================================================================================================
build-all: \
  build-ts
	@# intentionally blank, proxy for prerequisite.

all: clean \
  fmt \
  deps \
  build-all

ts_target_files := target/f7.js
ts_target_test_files := target/f7_test.js
ts_source_files := $(wildcard src/**.*)
grammar_source_files := $(wildcard src/main/g4**.*)

build-grammars: $(grammar_source_files)
	cd src/main/g4 && \
        antlr4 F7.g4 -o ../java/io/protobase/f7/antlr -visitor -package io.protobase.f7.antlr && \
        antlr4 F7.g4 -o ../js/antlr -visitor -Dlanguage=JavaScript

build-ts: $(ts_target_files)
	@# intentionally blank, proxy for prerequisite.

test-ts: $(ts_target_test_files)
	node target/f7_test.js

$(ts_target_files): $(ts_source_files)
	esbuild \
		src/main/js/Export.ts \
		--bundle \
		--outfile=target/f7.js \
		--format=iife \
		--platform=node \
		--target=esnext

$(ts_target_test_files): $(ts_source_files)
	esbuild \
		src/test/js/Index.ts \
		--bundle \
		--external:"antlr4/index" \
		--outfile=target/f7_test.js \
		--platform=node

deps:
	$(YARNINSTALL)

fmt:
	$(ESLINT)
	$(PRETTIER)

clean:
	rm -rf target/*

nuke: clean
	rm -rf node_modules

bootstrap-linux:
	sudo apt-get update && \
      sudo apt-get -y upgrade && \
      sudo apt-get install make -y && \
      sudo apt-get install npm -y && \
      npm install --global yarn

bootstrap-macos:
	brew install watch && \
      brew install node && \
      brew install yarn
