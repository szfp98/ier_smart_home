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
// Esős időjárás perceptus kezelése
+weather(rainy) : true
  <- .print("Rain detected, closing window.");
     !closeWindow.

// Terv az ablak becsukására
+!closeWindow
  <- .send(env, tell, closeWindow(window)).

+weather(rainy) : false
   <- .print("Sun is up, opening window.");
   !openWindow.
+!openWindow
    <- .send(env, tell, openWindow(window)).

