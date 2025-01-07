- Regras do jogo:

Sudoku é um jogo de raciocínio e lógica que consiste em preencher, com
números de 1 a 9, espaços em branco em uma tabela com nove linhas e nove
colunas. Para completar esses espaços, as seguintes regras devem ser
respeitadas:

1. Não repetir números na horizontal (linha).
2. Não repetir números na vertical (colunas).
3. Não repetir números nos quadrados de tamanho 3x3.

-  Implementação:

Atividade 1: (criando a configuração inicial do jogo)
Criar uma tela de boas-vindas para o jogador e perguntar ao usuário se
ele pretende gerar um jogo aleatório ou definir o próprio jogo.

Jogo aleatório:
O programa pergunta ao usuário quantos números ele deseja sortear e
criar o jogo.

Definir jogo:
O usuário define os valores iniciais do jogo com o seguinte formato
“([linha],[coluna],[valor])”. Por exemplo, (2,5,3) que representa
linha=2, coluna=5 e valor=3. O programa também deve permitir várias
entradas simultâneas, por exemplo, (2,5,3)(2,6,4)(2,9,1) que representa
linha=2, coluna=5, valor=3; linha=2, coluna=6, valor=4; e linha=2,
coluna=9, valor=1;
Quando o usuário estiver satisfeito decide por encerrar a inserção
dos valores iniciais digitando a entrada (-1, -1, - 1). Vale ressaltar
que o domínio das linhas, colunas e valores são inteiros no intervalo
[1,9].

Atividade 2 (vamos jogar?):
Após a definição da configuração inicial do jogo, nessa atividade deve
ser criada uma tela com a dinâmica do jogo com as seguintes opções:

Adicionar jogada: 
Para adicionar uma jogada o usuário entra com os dados relacionados
à linha, coluna e valor no seguinte formato “(linha,coluna, valor)” sem
espaços respeitando ao domínio de entrada. Entradas fora do formato
devem ser descartadas. Ou seja, uma entrada do tipo (2 , 5, 9), (1,2) ou
(0,0,0) deve ser descartada. 
Vale ressaltar que o usuário pode fornecer mais de uma entrada ao
mesmo tempo, e todas devem ser processadas. Por exemplo, para a entrada
(1,1,2)(2,2,3) as duas jogadas devem ser processadas e adicionadas caso
nenhum outro valor já esteja na linha e coluna selecionada. No caso de
rejeitar uma entrada, descreva o motivo dele ter sido rejeitada. Por
exemplo, caso a entrada (2,2,3) já tenha um valor na mesma posição, crie
uma mensagem dizendo “A entrada (2,2,3) não foi inserida, pois já possui
um valor atribuído”.

Remover jogada:
Para remover uma jogada o usuário fornece o valor da linha e coluna
a ser removida no formato “(linha,coluna)” sem espaço e respeitando o
domínio da entrada. Vale ressaltar que os valores definidos na Atividade
1 não podem ser removidos.

Verificar:
Para avaliar se o jogo está correto até o momento. Caso alguma das
três regras não sejam satisfeitas, o seu programa deve criar um
relatório descrevendo as quebras.

Dica:
Oferecer uma opção de dica, informando quais os valores podem ser
adicionados em uma posição específica. Para isso, o usuário deve
fornecer a linha e coluna no formato “(linha,coluna)” sem espaço e
respeitando o domínio da entrada. A dica deverá retornar o conjunto dos
valores que podem ser adicionados nessa posição.

Sair:
Para sair do jogo.
