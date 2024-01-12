https://dentall-test-api.onrender.com/api

## API Dokumentacija
### GET /user
Vraća listu svih korisnika

### GET /user/:id
Vraća korisnika s određenim id-om

### GET /user/match
Parametar: String:'name': ime korisnika</br>
Parametar: String:'surname': prezime korisnika</br>
Parametar: String:'birthDate': datum rođenja korisnika u formatu 'yyyy-MM-dd'</br>

Vraća korisnika koji se podudara s parametrima ili 400:Bad request ako ne postoji korisnik s tim parametrima

### POST /user/generate
Parametar: int:'count': broj korisnika koji će se nasumično generirati 

Vraća listu korisnika koji su generirani

### POST /user/add
Body: JSON objekt s podatcima novog korisnika(name, surname, email, phoneNumber, birthDate('yyyy-MM-dd'))</br>

Vraća objekt s id-em , imenom i prezimenom novog korisnika.

### GET /tretman
Vraća listu svih tretmana

### GET /tretman/:id
Vraća tretman s određenim id-om

### GET /tretman/zakljucan
Parametar: bool:'zakljucan': true ili false ovisno o tome želimo li dobiti zaključane tretmane ili ne</br>
Parametar: String:'after': datum u formatu 'yyyy-MM-dd-HH-mm-ss'

Vraća listu tretmana koji su zaključani nakon datuma i vremena specificiranog u parametru after

### POST /tretman/generate
Parametar: int:'count': broj tretmana koji će se nasumično generirati s podatcima dostupnih korisnika u bazi

Vraća listu tretmana koji su generirani

### PATCH /tretman/zakljucan/:id
Parametar: bool:'zakljucan': true ili false ovisno o tome želimo li zaključati ili otključati tretman

Vraća tretman s određenim id-om nakon primjene promjene zaključanosti