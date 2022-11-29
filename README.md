# Software-1
An application that stores products and parts associated with that product for a mechanic shop. Created using Java and FXML. 
Created as an assignment for a course titled "Software I." 

Assignment requirements are as follows:<br>
<br>
I.  User Interface<br>
  A.  Create a JavaFX application with a graphical user interface (GUI) based on the attached “Software 1 GUI Mock-Up.” You may use JavaFX with or without FXML to create your GUI, or you may use Scene Builder to create your FXML file; use of Swing is not permitted. The user interface (UI) should closely match the organization of the GUI layout and contain all UI components (buttons, text fields, etc.) in each of the following GUI mock-up forms:<br>
    1.  Main form<br>
    2.  Add Part form<br>
    3.  Modify Part form<br>
    4.  Add Product form<br>
    5.  Modify Product form<br>
<br>
  B.  Provide Javadoc comments for each class member throughout the code, and include a detailed description of the following in your comments:<br>
    • a logical or runtime error that you corrected in the code and how it was corrected<br>
    • a future enhancement that would extend the functionality of the application if it were to be updated<br>
<br>
<br>
II.  Application<br>
  C.  Create classes with data and logic that map to the UML class diagram and include the supplied Part class provided in the attached “Part.java.” Do not alter the provided class. Include all the classes and members as shown in the UML diagram. Your code should demonstrate the following:<br>
    •   inheritance<br>
    •   abstract and concrete classes<br>
    •   instance and static variables<br>
    •   instance and static methods<br>
  D.  Add the following functionalities to the Main form:<br>
    1.  The Parts pane<br>
      •   The Add button under the Parts TableView opens the Add Part form.<br>
      •   The Modify button under the Parts TableView opens the Modify Part form.<br>
      •   The Delete button under the Parts TableView deletes the selected part from the Parts TableView or displays a descriptive error message in the UI or in a dialog box if a part is not deleted.<br>
      •   When the user searches for parts by ID or name (partial or full name) using the text field, the application displays matching results in the Parts TableView. (Including a search button is optional.) If the part or parts are found, the application highlights a single part or filters multiple parts. If the part is not found, the application displays an error message in the UI or in a dialog box.<br>
    •   If the search field is set to empty, the table should be repopulated with all available parts.<br>
<br>
    2.  The Products pane<br>
      •   The Add button under the Products TableView opens the Add Product form.<br>
      •   The Modify button under the Products TableView opens the Modify Product form.<br>
      •   The Delete button under the Products TableView deletes the selected product (if appropriate) from the Products TableView or displays a descriptive error message in the UI or in a dialog box if a product is not deleted.<br>
      •   When the user searches for products by ID or name (partial or full name) using the text field, the application displays matching results in the Products TableView. (Including a search button is optional.) If a product or products are found, the application highlights a single product or products or filters multiple products. If a product or products are not found, the application displays an error message in the UI or in a dialog box.<br>
      •   If the search field is set to empty, the table should be repopulated with all available products<br>
    3.  Exit button<br>
      • The Exit button closes the application.<br>
<br>
E.  Add the listed functionalities to the following parts forms:<br>
  1.  The Add Part form<br>
    • The In-House and Outsourced radio buttons switch the bottom label to the correct value (Machine ID or Company Name).<br>
    •   The application auto-generates a unique part ID. The part IDs can be, but do not need to be, contiguous.<br>
      -   The part ID text field must be disabled.<br>
    •   The user should be able to enter a part name, inventory level or stock, a price, maximum and minimum values, and company name or machine ID values into active text fields.<br>
    •   After saving the data, users are automatically redirected to the Main form.<br>
    •   Canceling or exiting this form redirects users to the Main form.<br>
  2.  The Modify Part form<br>
    • The text fields populate with the data from the chosen part.<br>
    • The In-House and Outsourced radio buttons switch the bottom label to the correct value (Machine ID or Company Name) and swap In-House parts and Outsourced parts. When new objects need to be created after the Save button is clicked, the part ID should be retained.<br>
    • The user can modify data values in the text fields sent from the Main form except the part ID.<br>
    • After saving modifications to the part, the user is automatically redirected to the Main form.<br>
    • Canceling or exiting this form redirects users to the Main form.<br>
<br>
F.  Add the following functionalities to the following product forms:<br>
  1.  The Add Product form<br>
  •   The application auto-generates a unique product ID. The product IDs can be, but do not need to be, contiguous.<br>
  -   The product ID text field must be disabled and cannot be edited or changed.<br>
  •   The user should be able to enter a product name, inventory level or stock, a price, and maximum and minimum values.<br>
  •   The user can search for parts (top table) by ID or name (partial or full name). If the part or parts are found, the application highlights a single part or filters multiple parts. If the part or parts are not found, the application displays an error message in the UI or in a dialog box.<br>
  •   If the search field is set to empty, the table should be repopulated with all available parts.<br>
  •   The top table should be identical to the Parts TableView in the Main form.<br>
  •   The user can select a part from the top table. The user then clicks the Add button, and the part is copied to the bottom table. (This associates one or more parts with a product.)<br>
  •   The Remove Associated Part button removes a selected part from the bottom table. (This dissociates or removes a part from a product.)<br>
  •   After saving the data, the user is automatically redirected to the Main form.<br>
  •   Canceling or exiting this form redirects users to the Main form.<br>
Note: When a product is deleted, so can its associated parts without affecting the part inventory. The Remove Associated Part button removes a selected part from the bottom table. (This dissociates or removes a part from a product.)<br>
<br>
  2.  The Modify Product form<br>
    •   The text fields populate with the data from the chosen product, and the bottom TableView populates with the associated parts.<br>
    •   The user can search for parts (top table) by ID or name (partial or full name). If the part or parts are found, the application highlights a single part or filters multiple parts. If the part is not found, the application displays an error message in the UI or a dialog box.<br>
    •   If the search text field is set to empty, the table should be repopulated with all available parts.<br>
    •   The top table should be identical to the Parts TableView in the Main form.<br>
    •   The user may modify or change data values.<br>
      -   The product ID text field must be disabled and cannot be edited or changed.<br>
    •   The user can select a part from the top table. The user then clicks the Add button, and the part is copied to the bottom table. (This associates one or more parts with a product.)<br>
    •   The user may associate zero, one, or more parts with a product.<br>
    •   The user may remove or disassociate a part from a product.<br>
    •   After saving modifications to the product, the user is automatically redirected to the Main form.<br>
    •   Canceling or exiting this form redirects users to the Main form.<br>
    Note: The Remove Associated Part button removes a selected part from the bottom table. (This dissociates or removes a part from a product.)<br>
<br>
G.  Write code to implement input validation and logical error checks using a dialog box or message in the UI displaying a descriptive error message for each of the following circumstances:<br>
  •   Min should be less than Max; and Inv should be between those two values.<br>
  •   The user should not delete a product that has a part associated with it.<br>
  •   The application confirms the “Delete” and “Remove” actions.<br>
  •   The application will not crash when inappropriate user data is entered in the forms; instead, error messages should be generated<br>
