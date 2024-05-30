!start.

+!start : true <- 
    .print("Window Controller started").

+!set_weather(W) : 
    W == "windy" & .my_name(Name) <- 
    !close_window(Name);
    !open_window(Name).

+!close_window(Name) <- 
    .send(env, tell, closeWindow(window)).

+!open_window(Name) <- 
    .send(env, tell, openWindow(window)).
