!start.

+!start : true <- 
    .print("AC Controller started").

+!set_temperature(T) : 
    T < 25 & .my_name(Name) <- 
    !turn_on_ac(Name);
    !turn_off_ac(Name).

+!turn_on_ac(Name) <- 
    .send(env, tell, setTemperature(22)).

+!turn_off_ac(Name) <- 
    .send(env, tell, setTemperature(28)).
