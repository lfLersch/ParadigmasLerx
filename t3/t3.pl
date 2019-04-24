impar(1).
impar(X):- Y is mod(X,2), Y=1.

par(0).
par(X):- Y is mod(X,2), Y=0.


hasN([],0).
hasN([H|T],N) :- M is N - 1, hasN(T,M). 

inc99([],[]).
inc99([H1|T1],[H2|T2])  :- H2 is H1 + 99, inc99(T1,T2).

incN([],[],N).
incN([H1|T1],[H2|T2],N)  :- H2 is H1 + N, incN(T1,T2,N).

comment([],[]).
comment([H1|T1],[H2|T2]) :- string_concat("%%",H1, H2), comment(T1,T2).

onlyEven([],[]).
onlyEven([H1|T1],[H2|T2]) :- 
par(H1), H2 is H1, onlyEven(T1,T2);
impar(H1), onlyEven(T1,[H2|T2]).

countdown(0,[]).
countdown(N,[H|T]) :- H is N, X is N - 1, countdown(X,T).

nRandoms(0,[]).
nRandoms(N,[H|T]) :- random(1,100,H),X is N - 1, nRandoms(X,T).

potN0(0,[]).
potN0(N,[H|T]) :- pow(2,N,H), X is N - 1, potN0(2,X,T).