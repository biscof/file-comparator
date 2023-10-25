# File Comparator

[![Actions Status](https://github.com/biscof/java-project-71/workflows/hexlet-check/badge.svg)](https://github.com/biscof/java-project-71/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/454eb0f9b8262c0f42ce/maintainability)](https://codeclimate.com/github/biscof/java-project-71/maintainability)
[![Actions Status](https://github.com/biscof/java-project-71/workflows/build-and-test/badge.svg)](https://github.com/biscof/java-project-71/actions)
[![Test Coverage](https://api.codeclimate.com/v1/badges/454eb0f9b8262c0f42ce/test_coverage)](https://codeclimate.com/github/biscof/java-project-71/test_coverage)


## Introduction

File Comparator is a CLI utility for comparing two files and highlighting the differences between them. It was developed as a study project.


## Features

- supports YAML (YML) and JSON
- generates reports in plain text, stylish, and JSON formats

### Demos
- comparing JSON files: [demo](https://asciinema.org/a/CdibHBoblDqzJjsR2TkeUDrTO)
- comparing YAML files: [demo](https://asciinema.org/a/wsEql2jJzpiUqhWur8KqSeh7g)
- comparing files with embedded structures: [demo](https://asciinema.org/a/9seyreWnbqEGE2X65MS9ZfWhR)
- output in plain format: [demo](https://asciinema.org/a/d9EktDQn9lfuDoHULTUotQd5E)
- output in JSON format: [demo](https://asciinema.org/a/kc7qHgdP8SQMoZc0YHwXHLVyO)


## Dependencies

- Java 17
- Picocli 4.7
- JUnit 5.8


## Usage

1. Clone the repository and navigate to the app directory:

```bash
git clone https://github.com/biscof/file-comparator.git
cd file-comparator/app
```

2. To build this app, use this command:

```bash
make prepare
```

3. Compare two files:

- get the report in the stylish format (the default option):

```bash
build/install/app/bin/app <path-to-file-1> <path-to-file-2>
```

- get the report in your chosen format (stylish, plain, and JSON are available):

```bash
build/install/app/bin/app -f plain <path-to-file-1> <path-to-file-2>
```

4. For more information, use `-h` flag:

```bash
build/install/app/bin/app -h
```

### Report Formats

- **stylish**
```bash
{
  + follow: false
  + numbers: [1, 2, 3]
    setting1: Value 1
  - setting2: 200
  + setting3: {key=value}
    setting4: test
}
```

- **plain**
```bash
Property 'follow' was added with value: false
Property 'baz' was updated. From 'bas' to 'bars'
Property 'group2' was removed
Property 'subsribers' was added with value: [complex value]
```
