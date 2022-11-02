Employee Storage Service


I created a very basic front end for the application, so the service can be accessed and tested at localhost:8080/ from a browser.

Configuration:

	- an attached mySql database can be used to set the schema up as, well to import some existing data,
	- copy the project and import it to an IDE of choice,
	- in the project at location src/main/resources/ a file name application.properties can be found, change the access credentials for the database.
	- start the application from the IDE.
	
Overview:

	- After accessing the application at localhost, a user can see all of the existing active companies,
	- a company has a Name and active and deleted flags,
	- clicking on the Show all button, a user can list all companies, active, inactive and deleted,
	- From the same page a user can create a new company by filling the form and clicking on the Create company button,
	- Clicking on the View button, a user will be redirected to the details page of the company, where he can update the selected company's values,
	- Clicking on the Soft delete button, a user can mark the company as deleted, by updating the isDeleted flag,
	- Clicking on the Delete button, a user can delete the company from the Storage Service,
	- Clicking on the Show employees button, a user will be redirected to a new page listing all active employees for the selected company.
	
	- As with companies, the user can toggle between active and inactive employees,
	- an employee has a Name, Surname, Email, Address, Salary, Company name and active and deleted flags,
	- user can create, view, update, soft delete and delete the employees,
	- By clicking on the Calculate average salary, a user can see the average salary for the selected company by only taking active employees into consideration.
	
	- If a user tries to insert blank fields for company name, user name or surname, as well as a wrongly formatted email addres, the application will show an error message,
	- If a user tries to delete a company with employees, the application will show an error message.
