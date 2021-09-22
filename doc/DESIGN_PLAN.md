# OOLALA Design Plan
## NAMES
* Nayla Boorady
* Marcus Deans
* Maddie Demming

### Team Roles and Responsibilities
* Nayla:
  * f
* Marcus:
  * f
* Maddie:
  * F
  
### High Level Design
#### Logo Programming IDE
[Link to Project Details](https://courses.cs.duke.edu/fall21/compsci307d/assign/team/01_oolala/logo.php)

Design Questions:
* When does parsing need to take place and what does it need to start properly?
  * We need to be able to parse the user input line-by-line
  * Each line corresponds to one turtle instructions
  * We need to separate the line into the operation (left, right) and pixel count (integer)
  * To start properly, it needs an input that matches the right format
* What is the result of parsing and who receives it?
* When are errors detected and how are they reported?
  * Errors should be detected upon the parsing of each line
    * One incorrect input will not prevent entire program
  * If there is an error, show to user
  * User can skip the erroneous line and continue rest of program
* What do commands know, when do they know it, and how do they get it?
  * The commands like left, right, forward will correspond to methods
  * Each method will take an input as the old x,y and return the new x,y after that operation
  * The shared method that calculates the turtle's increment/movements will take both these values
  * That shared method will then pass the information to the frontend as described below
* How is the GUI updated after a command has completed execution?
  * The GUI is updated by creating a JavaFX Line (since that is the turtle's path) that connects the Turtle's prior point to the point after the Turtle's completion
  * The program will run in a continuous JavaFX animation
    * The backend parses the input
    * Each timestep in the JavaFX application, the backend will be called to increment turtle's position
    * The backend moves the turtle at constant speed to the goal and has the turtle's position at each timestep
    * Backend returns the old x,y and new x,y to the frontend
    * The GUI then draws a line connecting the two positions
    * The backend then sets old x,y to new x,y
    * Backend can then get new x,y coordinates based on the user position

#### L- System Visualizer
[Link to System Visualizer](https://courses.cs.duke.edu/fall21/compsci307d/assign/team/01_oolala/lsystem.php)


#### Darwin Simulator
[Link to Darwin Simulator](https://courses.cs.duke.edu/fall21/compsci307d/assign/team/01_oolala/darwin.php)

### CRC Card Classes

This class's purpose or value is to represent a customer's order:
![Order Class CRC Card](order_crc_card.png "Order Class")


This class's purpose or value is to represent a customer's order:

|Order| |
|---|---|
|boolean isInStock(OrderLine)         |OrderLine|
|double getTotalPrice(OrderLine)      |Customer|
|boolean isValidPayment (Customer)    | |
|void deliverTo (OrderLine, Customer) | |


This class's purpose or value is to represent a customer's order:
```java
public class Order {
     // returns whether or not the given items are available to order
     public boolean isInStock (OrderLine items)
     // sums the price of all the given items
     public double getTotalPrice (OrderLine items)
     // returns whether or not the customer's payment is valid
     public boolean isValidPayment (Customer customer)
     // dispatches the items to be ordered to the customer's selected address
     public void deliverTo (OrderLine items, Customer customer)
 }
 ```
 

### Use Cases 

 * The user types 'fd 50' in the command window, sees the turtle move in the display window leaving a trail, and has the command added to the environment's history.
```java
 OrderLine items = new OrderLine();
 if (order.isInStock(items)) {
     total = order.getTotalPrice(items);
 }
```

 * The user loads a file of commands, sees the turtle move in the display window leaving a trail, and has the command added to the environment's history.
```java
 OrderLine items = new OrderLine();
 if (order.isInStock(items)) {
     total = order.getTotalPrice(items);
 }
```

 * The user types '50 fd' in the command window and sees an error message that the command was not formatted correctly.
```java
 OrderLine items = new OrderLine();
 if (order.isInStock(items)) {
     total = order.getTotalPrice(items);
 }
```

 * The user clicks in the display window to set a new Home location.
```java
 OrderLine items = new OrderLine();
 if (order.isInStock(items)) {
     total = order.getTotalPrice(items);
 }
```

 * The user changes the color of the environment's background.
```java
 OrderLine items = new OrderLine();
 if (order.isInStock(items)) {
     total = order.getTotalPrice(items);
 }
```

