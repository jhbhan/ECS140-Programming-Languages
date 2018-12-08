shuffle([L1],[L2],[L1,L2]).
shuffle([H1|T1],[H2|T2],[H31,H32|T3]) :- shuffle([H1],[H2],[H31,H32]),shuffle(T1,T2,T3).
double([L1],[L1,L1]).
double([H1|T1],[H21,H22|T2]) :- double([H1],[H21,H22]),double(T1,T2).
no_duplicates([],[]).
no_duplicates([H1|T1], L1) :- member(H1, T1),no_duplicates(T1, L1).
no_duplicates([H1|T1], [H2|T2]) :- \+member(H1, T2),no_duplicates(T1, T2).
same_element([],[]).
same_element([H1|T1],L1) :- same_element(T1,L2),help(H,T,L2).
help(H, [H|T],T).
help(H,[X|T1],[X|T2]) :- help(H,T1,T2).