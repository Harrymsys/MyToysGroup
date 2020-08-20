# MyToysGroup
### This project contains the microservice for the product module in MyToysGroup
# 1) Class Diagram for this project 
![](https://github.com/Harrymsys/MyToysGroup/blob/master/mytoysgroup_design/class_diagram/MyToysGroup_ClassDiagram.png)


# 2) API Documentation for the Product module in MyToysGroup
####  1. API to upload the products as CSV into the datastore in MyToysGroup 

**API URL**: [http://{BASE_URL}/product/upload](http://%7bBASE_URL%7d/product/upload)

**API Method Type**: POST

**API Content Type**: text/csv

**Upload Field Name**: productcsv

**Response**: Json

**Also added the validation for invalid headers / no input etc.,**

