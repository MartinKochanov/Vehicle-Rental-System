Firstly, I'm structuring my class hierarchy and creating a class which is going to manage the invoice printing:<br>
<br>
Base Class: Vehicle,<br>
Subclasses: Car, Motorcycle, CargoVan with attributes and methods tailored to each vehicle type (safetyRating for Car, riderAge for Motorcycle, and driverExperience for CargoVan).<br>
Rental Class: Manages invoice printing.<br>
<br>
Afterward, I am creating utility classes:<br>
Validations: Ensures integrity of user inputs (e.g., customer details, vehicle specifics, rental periods).<br>
CalculationUtils: Performs precise calculations for rental costs, insurance, and discounts based on vehicle type and rental parameters.<br>
<br>
And enums:<br>
VehicleTypesEnums: Stores all of the available vehicle types.<br>
<br>
Then I am moving on to the main entry point of the application (Main Class), which orchestrates core functionalities such as:<br>
Initializes rentals and manages user interactions via console input.<br>
Validates inputs using Validations for accuracy and completeness.<br>
Utilizes CalculationUtils to compute total rental costs and generate invoices.<br>
<br>
Lastly and most importantly, checking if everything works as expected! 😃
