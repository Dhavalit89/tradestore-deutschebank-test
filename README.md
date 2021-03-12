# tradestore-deutschebank-test
deutschebank interview coding test

1.	During transmission if the lower version is being received by the store it will reject the trade and throw an exception. If the version is same it will override the existing record.
2.	Store should not allow the trade which has less maturity date then today date.
3.	Store should automatically update expire flag if in a store the trade crosses the maturity date.


This project contains spring boot application with in build H2 database

Feature:
1) Rest ennpoints to add/update/delete tradestore
2) Testcase wich test alll the scenarions mentioned above
3) scheduler task to delete trades having trade dates greater than maturity date
