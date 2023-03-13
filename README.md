# Searcher

## Recursive search for a text within files in a specified directory. 
## Usage:

```shell
java Searcher search_text [path] [number_of_lines_after_result] [-verbose] [extention]
```
## Min usage:

```shell
java Searcher return:
```

it will search for "return:" keyword in the directory where Search.class is located and create a file output123Ver1.txt and result123Ver1.txt.

## Advanced usage

```shell
C:\Users\IdeaProjects\ZF projects\FileList\out\production\FileList>java Searcher return: C:\Users\Z0216921\files 2 -verbose .java
```
it will scann all files with .java extention in C:\Users\IdeaProjects\ZF projects\FileList\out\production\FileList directory, print to the console results (-verbose option) and save marged files to output123Ver1.txt and results to result123Ver1.txt.
