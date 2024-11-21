# Trabalho Prático 02 - Grafos

Este repositório contém a implementação do **Trabalho Prático 02** da disciplina **Algoritmos e Estruturas de Dados III** do curso de Bacharelado em Sistemas de Informação. O objetivo é desenvolver um programa em **C**, **C++**, **Java**, **JavaScript** ou **PHP** que permita manipular grafos com diversas funcionalidades.

## Objetivo
O trabalho visa praticar conceitos básicos de **grafos**, implementando um programa capaz de:
1. Importar um grafo a partir de um arquivo de texto.
2. Criar um grafo vazio a partir de um número de vértices informado pelo usuário.
3. Exibir as adjacências (matriz ou lista de adjacências).
4. Consultar se um vértice é adjacente a outro.
5. Inserir uma nova aresta.
6. Inserir várias arestas em conjunto.
7. Remover arestas.
8. Editar a coordenada de vértices.
9. Consultar o primeiro adjacente de um vértice.
10. Consultar o próximo adjacente de um vértice a partir de um adjacente informado.
11. Consultar a lista completa de adjacentes de um vértice.
12. Exportar o grafo para um arquivo de texto.
13. (Opcional) Exibir o grafo de maneira visual utilizando tecnologias como Java Swing, JavaFX, HTML SVG, HTML Canvas, OpenGL, entre outras.

## Formato de Importação e Exportação
O grafo será importado e exportado em um arquivo de texto com o seguinte formato:

1. **Linha 1**: Indica se o grafo é direcionado (`sim` ou `nao`).
2. **Linha 2**: Número de vértices.
3. **Próximas linhas**: Identificação e coordenadas `(x, y)` de cada vértice (em escala de 0 a 100).
4. **Linha seguinte**: Quantidade de arestas.
5. **Próximas linhas**: Origem, destino e peso de cada aresta.

#### Exemplo de entrada:
```text
direcionado=nao
10
0 1 1
1 50 20
2 10 30
3 12 50
4 15 70
5 20 90
6 25 11
7 30 12
8 35 15
9 40 20
10
0 1 50
0 9 20
1 2 35
2 3 1
3 4 10
4 5 28
5 6 13
6 7 41
7 8 8
8 9 39
```