!start.

+!start : true <-
    .print("Light Controller started").

+!set_time(T) :
    T >= 18 | T < 6 & .my_name(Name) <-
    !turn_on_light(Name);
    !turn_off_light(Name).

+!turn_on_light(Name) <-
    .send(env, tell, toggleLight).

+!turn_off_light(Name) <-
    .send(env, tell, toggleLight).
