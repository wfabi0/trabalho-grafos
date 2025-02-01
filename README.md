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

### Parte dois:

14. Ajustar a funcionalidade de importação do grafo para que ela também leia e armazene os nomes dos vértices. 
15. Implementar a funcionalidade de edição dos nomes dos vértices. 
16. Implementar o algoritmo de busca em profundidade (o código deve gerar saídas para o usuário acompanhar o processo). 
17. Implementar o algoritmo de busca em largura (o código deve gerar saídas para o usuário acompanhar o processo). 
18. Implementar um algoritmo de árvore geradora mínima (Kruskal ou Prim), fazendo isso de forma que o programa gere informações textuais para o usuário acompanhar o processo e o resultado. 
19. Implementar o algoritmo de menor caminho (Dijkstra), de forma que o usuário possa calcular e visualizar textualmente o menor caminho entre um par de vértices digitando o nome deles. 
20. Elaborar o arquivo texto e o desenho de um grafo (não direcionado, conectado, com ao menos 18 vértices) a ser usado para testar a aplicação, podendo usar como base o exemplo disponibilizado aqui.
21. (Opcional) Exibir o grafo e os resultados dos algoritmos de forma visual.
22. (Opcional) Exibir a execução e os resultados dos algoritmos de forma visual com animação.

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
7
0 0 0 Mons Agnes
1 0 0 Vladivostok
2 0 0 Mount Gundabad
3 0 0 Mons La Hire
4 0 0 Coruscant
5 0 0 Mons Wolf
6 0 0 Cafarnaun
12
0 2 7
0 4 10
0 5 2
0 3 9
2 4 4
2 6 7
2 3 3
3 1 5
4 1 6
5 1 4
5 4 9
6 5 8
```

#### Exemplo de entrada 2:
```text
direcionado=sim
18
0 5 5 Ronaldo
1 10 10 Rosiney
2 15 15 Rogerio
3 20 20 Roberto
4 25 25 Rafael
5 30 30 Roni
6 35 35 Reinaldo
7 40 40 Raul
8 45 45 Rosiana
9 50 50 Ricardo
10 55 55 Rosangela
11 60 60 Rodrigol
12 65 65 Rosineia
13 70 70 Rafaela
14 75 75 Rose
15 80 80 Raimundo
16 85 85 Rapucha
17 90 90 Rosimar
25
0 1 5
0 2 10
1 3 7
2 4 8
3 5 6
4 6 9
5 7 12
6 8 11
7 9 15
8 10 14
9 11 13
10 12 16
11 13 17
12 14 18
13 15 19
14 16 20
15 17 21
16 0 22
17 1 23
2 5 24
3 7 25
8 13 26
9 16 27
10 14 28
11 15 29
```