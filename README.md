# Number to words converter

Simple Java command-line application that converts a number to Polish word(s) representation.

A description of the implemented algorithm can be found here (PL): 
http://www.algorytm.org/inne/zamiana-liczby-na-slowa-z-polska-gramatyka.html

## Compiling

```console
mkdir target
javac -d target pl/rmigacz/jbc/ntwc/*
```

## Usage

```console
cd target
java pl.rmigacz.jbc.ntwc.NumberToWordsConverter <number>
```