Firstly im structuring my class hierarchy and creating a class which is goint to manage the invoice printing:
  Base Class: Vehicle,
  Subclasses: Car, Motorcycle, CargoVan,
  Rental Class: managing the invoice printing
  Adding attributes and methods tailored to each vehicle type (safetyRating for Car, riderAge for Motorcycle and driverExperience for CargoVan).

After i am creating utility classes for:
  Validations: Ensures integrity of user inputs (e.g., customer details, vehicle specifics, rental periods).
  CalculationUtils: Performs precise calculations for rental costs, insurance, and discounts based on vehicle type and rental parameters.
And enums:
  VehicleTypesEnums: Storing all of the available vehicle types.

Then i am moving onto the main entrypoint of the application (Main Class) and orchestrates core functionalities like:
  Initializes rentals and manages user interactions via console input.
  Validates inputs using Validations for accuracy and completeness.
  Utilizes CalculationUtils to compute total rental costs and generate invoices.

