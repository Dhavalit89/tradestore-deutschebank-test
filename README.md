# tradestore-deutschebank-test
deutschebank interview coding test

**This project contains spring boot application with in build H2 database**

Features:
1) Rest ennpoints to add/update/delete tradestore
2) Testcase which test alll the scenarions mentioned bellow in problem statement
3) scheduler task to delete trades having trade dates greater than maturity date



**Problem Statement**
There is a scenario where thousands of trades are flowing into one store, assume any way of transmission of trades. We need to create a one trade store, which stores the trade in the following order

Trade Id	Version	Counter-Party Id	Book-Id	Maturity Date	Created Date	Expired
T1	1	CP-1	B1	20/05/2020	<today date>	N
T2	2	CP-2	B1	20/05/2021	<today date>	N
T2	1	CP-1	B1	20/05/2021	14/03/2015	N
T3	3	CP-3	B2	20/05/2014	<today date>	Y

There are couples of validation, we need to provide in the above assignment
1.	During transmission if the lower version is being received by the store it will reject the trade and throw an exception. If the version is same it will override the existing record.
2.	Store should not allow the trade which has less maturity date then today date.
3.	Store should automatically update expire flag if in a store the trade crosses the maturity date.

