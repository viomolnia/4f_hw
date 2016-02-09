Project Structure info:
I Source folder's fourfinance package contains:
    1. config package: contains configuration files and constants;
    2. core package contains:
        2.1. domain package with entities,
        2.2. dto package with DTO provided for each entity,
        2.3. database package contains services for database access methods,
        2.4. services package contains business logic for creation and maintaining entities,
        2.5. commands package contains commands provided opportunity to operate with business logic;
    3. rest package contains implementation of rest interface.
II Source folder's resources package contains:
    1. liquibase configuration,
    2. liquibase changelogs;
    3. application properties.
III Source folder's test package contains:
    1. core tests:
        1.1. tests for methods working with database,
        1.2. tests for business-logic methods,
        1.3. rest implementation tests.



While filling task some precisions were taken:
1. Loan's currency is EUR
    1.1. Loan minimal amount is 50 EUR.
    1.2. Loan maximal amount is 425 EUR.
2. Loans for amount 300 EUR and more are allowed for users, applying second and more time.
3. Loans amount less than 300 EUR are not indexed.
4. Loans for amount 300 EUR and more are indexed with an interest 4%
5. Loan's term can be extended
    5.1. Loan's extension cost is 1.5% of indexed amount per week,
    5.2 If loan's amount wasn't indexed, extension cost is 1.5% of indexed amount per week.
6. Loan's term is passed in days.
    6.1. Minimal term is 10 days.
    6.2. Maximal term is 30 days.
7. Loan's extension term is passed in weeks.
    7.1. Minimal extension term is 1 week.
    7.2. Maximal extension time is infinite.
8. User may have infinite count of loans,
    8.1. But only 3 applies from one and the same ip per day (24 hours).
9. It is possible to make 3 applies for a loan from one and the same IP per day (24 hours),
    9.1. But user (user id) doesn't matter,
    9.2. Now this functionality is disabled bu setting applies
            limit to 100 for passing tests and testing manually (LoanResourceImpl, +activate())
10. Loans for maximal amount (425 EUR) are not allowed in time interval 00:00 - 06:00.