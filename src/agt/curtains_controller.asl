!start.

+!start : true <-
    .print("Curtain Controller started").

+!set_time(T) :
    T > 18 | T < 6 & .my_name(Name) <-
    !close_curtains(Name);
    !open_curtains(Name).

+!close_curtains(Name) <-
    .send(env, tell, closeCurtains).

+!open_curtains(Name) <-
    .send(env, tell, openCurtains).
