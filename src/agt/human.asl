+!morning : true <- .print("Morning: Opening curtains"); open_curtains.
+!afternoon : true <- .print("Afternoon: Adjusting curtains"); adjust_curtains.
+!evening : true <- .print("Evening: Closing curtains"); close_curtains.

+open_curtains : true <- .send(env, tell, open_curtains).
+adjust_curtains : true <- .send(env, tell, adjust_curtains).
+close_curtains : true <- .send(env, tell, close_curtains).