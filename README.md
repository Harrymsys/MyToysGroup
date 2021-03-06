# MyToysGroup
### This project contains the microservice for the product module in MyToysGroup

# Technical Stack 

#### Microservice - Spring Boot (Version - 2.3.3)
#### DataStore - ElasticSearch (Version - 7.8.0)
#### Junit - SpringBoot Mockmvc and Junit (Version - 5) 
#### Jacaco - Test Coverage (Version - 0.8.5) [We have **91.5 % test coverage** for this project (check the code coverage section)]
#### Docker-Compose - (Version - 2)
#### UML Diagram - (ObjectAid) 
#### Java - (Version - 8) 
#### Single click deployment script (Bash) 


# 1) Class Diagram for this project 
![](https://github.com/Harrymsys/MyToysGroup/blob/master/mytoysgroup_design/class_diagram/MyToysGroup_ClassDiagram.png)


# 2) API Documentation for the Product module in MyToysGroup
###  1. API to upload the products as CSV into the datastore in MyToysGroup 

**API URL**: [http://{BASE_URL}/product/upload](http://%7bBASE_URL%7d/product/upload)

**API Method Type**: POST

**API Content Type**: text/csv

**Upload Field Name**: productcsv

**Response**: Json

**Also added the validation for invalid headers / no input etc.,**

![](https://github.com/Harrymsys/MyToysGroup/blob/master/mytoysgroup_testcoveragereport/supporting_images/pic1.PNG)

###  2. API to GET / GLOBAL / EXTENSIVE SEARCH on the products of MyToysGroup 
**API URL**: http://{BASE_URL}/product

**API Method Type**: GET 

**Response**: Json 

	                  {
                      "data": [XXXX],
                      "total": 1200,
                      "totalRecordInCurrentSearch": 20,
                      "scrollId": "XXXX"
                    }
                    
                    
**Possible Inputs**: 
	This service is used to get all the products based on the following criteria
	 	- Search Keyword on the field like ID, NAME, BRAND, STOCK
	 	- Sort on all the fields 
	 	- Limit by Size
	 	- Pagination
    
**Search Criteria 1**: API to get all the data of the product 

**Search Criteria 2**: API to get all the data which matches to the search text 

**Search Criteria 3**: API to get all the data which will be sorted by accepted fields

**Search Criteria 4**: API to get only the limited sets of data based on limit size 

**Search Criteria 5**: API to get data based on pagination.

**Search Criteria 6**: API to get data based on all the combinations of search, sort by field, limit 


## Case 1

**Search Criteria 1**: API to get all the data of the product 

http://192.168.56.101:8080/product
                    
![](https://github.com/Harrymsys/MyToysGroup/blob/master/mytoysgroup_testcoveragereport/supporting_images/pic2.PNG)

## Case 2 

**Search Criteria 2**: API to get all the data which matches to the search text 

http://192.168.56.101:8080/product?search=Tommy

![](https://github.com/Harrymsys/MyToysGroup/blob/master/mytoysgroup_testcoveragereport/supporting_images/pic3.PNG)

## Case 3

**Search Criteria 3**: API to get all the data which will be sorted by accepted fields

http://192.168.56.101:8080/product?sort=NAME

**Accepted Fields** : "ID", "NAME", "PRICE", "OLD_PRICE", "STOCK", "BRAND"

![](https://github.com/Harrymsys/MyToysGroup/blob/master/mytoysgroup_testcoveragereport/supporting_images/pic4.PNG)

## Case 4

**Search Criteria 4**: API to get only the limited sets of data based on limit size 

http://192.168.56.101:8080/product?size=20

![](https://github.com/Harrymsys/MyToysGroup/blob/master/mytoysgroup_testcoveragereport/supporting_images/pic5.PNG)

## Case 5 

**Search Criteria 5** : API to get data based on pagination.

In any search call, we will have scroll id used for pagination 

![](https://github.com/Harrymsys/MyToysGroup/blob/master/mytoysgroup_testcoveragereport/supporting_images/pic6.PNG)

http://192.168.56.101:8080/product?scrollId=FGluY2x1ZGVfY29udGV4dF91dWlkDXF1ZXJ5QW5kRmV0Y2gBFE5rZk5ESFFCZUZVc3VfeWZ4RjFTAAAAAAAAAwgWbTBGcDhUNHdSV2lUcER5Nm4zY3FOZw==

![](https://github.com/Harrymsys/MyToysGroup/blob/master/mytoysgroup_testcoveragereport/supporting_images/pic7.PNG)

## Case 6

**Search Criteria 6**: API to get data based on all the combinations of search, sort by field, limit

http://192.168.56.101:8080/product?search=Tommy&sort=NAME&size=30

![](https://github.com/Harrymsys/MyToysGroup/blob/master/mytoysgroup_testcoveragereport/supporting_images/pic8.PNG)

###  3. API to GET / GLOBAL / EXTENSIVE SEARCH on the products of MyToysGroup using RANGE Query 

## Case 7

**API URL**: http://{BASE_URL}/product

**API Method Type**: POST 

**Response**: Json 

  {
   "data": [XXXX],
   "total": 1200,
   "totalRecordInCurrentSearch": 20,
   "scrollId": "XXXX" 
   }

**Possible Input**:

{ 
  "field": "PRICE",
  "startvalue”: 10,
   "endvalue”: 20
}

**Accepted Values in field**: PRICE and OLD_PRICE

**Search Criteria 7**: API to get filter the data by range

http://localhost:8080/product

![](https://github.com/Harrymsys/MyToysGroup/blob/master/mytoysgroup_testcoveragereport/supporting_images/pic9.PNG)

## Case 8 :

Pagination can also be done using the scrollId (as described in API 2 for Range Query too)


# 3) Test Coverage Report 

# We have 91.5% test coverage report in this project currently 

![](https://github.com/Harrymsys/MyToysGroup/blob/master/mytoysgroup_testcoveragereport/CoverageReport_MyToysGroup.PNG)


To get the complete traversed detail on test coverage report, please clone this repository and check this html file : 
https://github.com/Harrymsys/MyToysGroup/blob/master/mytoysgroup_testcoveragereport/index.html (as it won't be visible in github UI)

# 4) Deployment 

## Prerequisite 

docker-compose should be installed in the linux environment : https://docs.docker.com/compose/install/

## This project is completely dockerized and will run in a single click : 

**Step 1** : Clone this repository 

**Step 2** : Change the IP Address (Optional - If needed) in the property file : MyToysGroup/mytoysgroup/property/microservice.properties

![](https://github.com/Harrymsys/MyToysGroup/blob/master/mytoysgroup_testcoveragereport/supporting_images/pic9.2.PNG)

**Step 3** : Run this script to start the service : https://github.com/Harrymsys/MyToysGroup/blob/master/mytoysgroup/run.sh

                          bash run.sh
	
![](https://github.com/Harrymsys/MyToysGroup/blob/master/mytoysgroup_testcoveragereport/supporting_images/pic10.PNG)

# This will start the ElasticSearch and also the Spring Boot microservice

![](https://github.com/Harrymsys/MyToysGroup/blob/master/mytoysgroup_testcoveragereport/supporting_images/pic11.PNG)
