begin_version
3
end_version
begin_metric
0
end_metric
10
begin_variable
var0
-1
3
Atom shakey-in-room(room1)
Atom shakey-in-room(room2)
Atom shakey-in-room(room3)
end_variable
begin_variable
var1
-1
3
Atom box-in-room(b, room1)
Atom box-in-room(b, room2)
Atom box-in-room(b, room3)
end_variable
begin_variable
var2
-1
2
Atom light-on(room3)
NegatedAtom light-on(room3)
end_variable
begin_variable
var3
-1
2
Atom light-on(room2)
NegatedAtom light-on(room2)
end_variable
begin_variable
var4
-1
2
Atom light-on(room1)
NegatedAtom light-on(room1)
end_variable
begin_variable
var5
-1
5
Atom holding-in-left(ball)
Atom holding-in-right(ball)
Atom object-in-room(ball, room1)
Atom object-in-room(ball, room2)
Atom object-in-room(ball, room3)
end_variable
begin_variable
var6
-1
5
Atom holding-in-left(rubber-duck)
Atom holding-in-right(rubber-duck)
Atom object-in-room(rubber-duck, room1)
Atom object-in-room(rubber-duck, room2)
Atom object-in-room(rubber-duck, room3)
end_variable
begin_variable
var7
1
2
Atom new-axiom@0()
NegatedAtom new-axiom@0()
end_variable
begin_variable
var8
1
2
Atom new-axiom@1()
NegatedAtom new-axiom@1()
end_variable
begin_variable
var9
0
2
Atom new-axiom@2()
NegatedAtom new-axiom@2()
end_variable
0
begin_state
1
0
1
1
1
3
2
0
0
0
end_state
begin_goal
1
9 1
end_goal
37
begin_operator
drop-left ball room1
1
0 0
1
0 5 0 2
1
end_operator
begin_operator
drop-left ball room2
1
0 1
1
0 5 0 3
1
end_operator
begin_operator
drop-left ball room3
1
0 2
1
0 5 0 4
1
end_operator
begin_operator
drop-left rubber-duck room1
1
0 0
1
0 6 0 2
1
end_operator
begin_operator
drop-left rubber-duck room2
1
0 1
1
0 6 0 3
1
end_operator
begin_operator
drop-left rubber-duck room3
1
0 2
1
0 6 0 4
1
end_operator
begin_operator
drop-right ball room1
1
0 0
1
0 5 1 2
1
end_operator
begin_operator
drop-right ball room2
1
0 1
1
0 5 1 3
1
end_operator
begin_operator
drop-right ball room3
1
0 2
1
0 5 1 4
1
end_operator
begin_operator
drop-right rubber-duck room1
1
0 0
1
0 6 1 2
1
end_operator
begin_operator
drop-right rubber-duck room2
1
0 1
1
0 6 1 3
1
end_operator
begin_operator
drop-right rubber-duck room3
1
0 2
1
0 6 1 4
1
end_operator
begin_operator
go-to-room room1 room2
0
1
0 0 0 1
1
end_operator
begin_operator
go-to-room room2 room1
0
1
0 0 1 0
1
end_operator
begin_operator
go-to-room room2 room3
0
1
0 0 1 2
1
end_operator
begin_operator
go-to-room room2 room3
0
1
0 0 1 2
1
end_operator
begin_operator
go-to-room room3 room2
0
1
0 0 2 1
1
end_operator
begin_operator
go-to-room room3 room2
0
1
0 0 2 1
1
end_operator
begin_operator
pick-up-left ball room1
3
4 0
7 1
0 0
1
0 5 2 0
1
end_operator
begin_operator
pick-up-left ball room2
3
3 0
7 1
0 1
1
0 5 3 0
1
end_operator
begin_operator
pick-up-left ball room3
3
2 0
7 1
0 2
1
0 5 4 0
1
end_operator
begin_operator
pick-up-left rubber-duck room1
3
4 0
7 1
0 0
1
0 6 2 0
1
end_operator
begin_operator
pick-up-left rubber-duck room2
3
3 0
7 1
0 1
1
0 6 3 0
1
end_operator
begin_operator
pick-up-left rubber-duck room3
3
2 0
7 1
0 2
1
0 6 4 0
1
end_operator
begin_operator
pick-up-right ball room1
3
4 0
8 1
0 0
1
0 5 2 1
1
end_operator
begin_operator
pick-up-right ball room2
3
3 0
8 1
0 1
1
0 5 3 1
1
end_operator
begin_operator
pick-up-right ball room3
3
2 0
8 1
0 2
1
0 5 4 1
1
end_operator
begin_operator
pick-up-right rubber-duck room1
3
4 0
8 1
0 0
1
0 6 2 1
1
end_operator
begin_operator
pick-up-right rubber-duck room2
3
3 0
8 1
0 1
1
0 6 3 1
1
end_operator
begin_operator
pick-up-right rubber-duck room3
3
2 0
8 1
0 2
1
0 6 4 1
1
end_operator
begin_operator
push-to-room b room1 room2
1
0 0
1
0 1 0 1
1
end_operator
begin_operator
push-to-room b room2 room1
1
0 1
1
0 1 1 0
1
end_operator
begin_operator
push-to-room b room2 room3
1
0 1
1
0 1 1 2
1
end_operator
begin_operator
push-to-room b room3 room2
1
0 2
1
0 1 2 1
1
end_operator
begin_operator
turn-on-light room1
2
1 0
0 0
1
0 4 1 0
1
end_operator
begin_operator
turn-on-light room2
2
1 1
0 1
1
0 3 1 0
1
end_operator
begin_operator
turn-on-light room3
2
1 2
0 2
1
0 2 1 0
1
end_operator
33
begin_rule
2
5 0
6 0
8 0 1
end_rule
begin_rule
2
5 0
6 2
8 0 1
end_rule
begin_rule
2
5 0
6 3
8 0 1
end_rule
begin_rule
2
5 0
6 4
8 0 1
end_rule
begin_rule
2
5 1
6 1
7 0 1
end_rule
begin_rule
2
5 1
6 2
7 0 1
end_rule
begin_rule
2
5 1
6 3
7 0 1
end_rule
begin_rule
2
5 1
6 4
7 0 1
end_rule
begin_rule
2
5 2
6 0
8 0 1
end_rule
begin_rule
2
5 2
6 1
7 0 1
end_rule
begin_rule
2
5 2
6 2
7 0 1
end_rule
begin_rule
2
5 2
6 2
8 0 1
end_rule
begin_rule
2
5 2
6 3
7 0 1
end_rule
begin_rule
2
5 2
6 3
8 0 1
end_rule
begin_rule
2
5 2
6 4
7 0 1
end_rule
begin_rule
2
5 2
6 4
8 0 1
end_rule
begin_rule
2
5 3
6 0
8 0 1
end_rule
begin_rule
2
5 3
6 1
7 0 1
end_rule
begin_rule
2
5 3
6 2
7 0 1
end_rule
begin_rule
2
5 3
6 2
8 0 1
end_rule
begin_rule
2
5 3
6 3
7 0 1
end_rule
begin_rule
2
5 3
6 3
8 0 1
end_rule
begin_rule
2
5 3
6 4
7 0 1
end_rule
begin_rule
2
5 3
6 4
8 0 1
end_rule
begin_rule
2
5 4
6 0
8 0 1
end_rule
begin_rule
2
5 4
6 1
7 0 1
end_rule
begin_rule
2
5 4
6 2
7 0 1
end_rule
begin_rule
2
5 4
6 2
8 0 1
end_rule
begin_rule
2
5 4
6 3
7 0 1
end_rule
begin_rule
2
5 4
6 3
8 0 1
end_rule
begin_rule
2
5 4
6 4
7 0 1
end_rule
begin_rule
2
5 4
6 4
8 0 1
end_rule
begin_rule
2
5 4
6 4
9 0 1
end_rule
