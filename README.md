Firstly im structuring my class hierarchy and creating a class which is goint to manage the invoice printing:<br>
  Base Class: Vehicle,<br>
  Subclasses: Car, Motorcycle, CargoVan with attributes and methods tailored to each vehicle type (safetyRating for Car, riderAge for Motorcycle and driverExperience for CargoVan).<br>
  Rental Class: managing the invoice printing<br>
<br>
After i am creating utility classes for:<br>
  Validations: Ensures integrity of user inputs (e.g., customer details, vehicle specifics, rental periods).<br>
  CalculationUtils: Performs precise calculations for rental costs, insurance, and discounts based on vehicle type and rental parameters.<br>
And enums:<br>
  VehicleTypesEnums: Storing all of the available vehicle types.<br>
<br>
Then i am moving onto the main entrypoint of the application (Main Class) and orchestrates core functionalities like:<br>
  Initializes rentals and manages user interactions via console input.<br>
  Validates inputs using Validations for accuracy and completeness.<br>
  Utilizes CalculationUtils to compute total rental costs and generate invoices.<br>

Lastly and most importantly checking if everrything works as expexted!😃

