### Uitleg:

**Singleton Patroon:**
De klasse `Minesweeper` heeft nu een privé statische instantievariabele en een privéconstructor. De constructor is privé om externe instantiatie van de klasse te voorkomen.

**`getInstance()` Methode:**
De openbare `getInstance()` methode wordt gebruikt om de singleton-instantie van de `Minesweeper` klasse op te halen. Als de instantie null is, maakt het een nieuwe instantie aan; anders retourneert het de bestaande instantie.

**Waarom is het Singleton Patroon nuttig:**

- *Enkel Toegangspunt:* Zorgt ervoor dat er slechts één instantie van de klasse is, wat een enkel toegangspunt tot die instantie biedt.

- *Globale Status:* Maakt het behoud van een wereldwijde status voor het spel mogelijk, waardoor consistentie in het spelverloop wordt gegarandeerd en conflicterende toestanden worden vermeden.

- *Delen van Middelen:* Als de spelinstantie veel middelen vereist, voorkomt Singleton overbodige instantiatie en zorgt het voor het delen van middelen.

Nu kun je, wanneer je de spelstatus wilt benaderen of aanpassen, dit doen via de singleton-instantie, wat zorgt voor een meer gecontroleerde en consistente spelervaring.


**Observer Patroon:**
Het Observer Patroon wordt gebruikt om een een-op-veel afhankelijkheid te creëren tussen het `Minesweeper` object (observable) en meerdere `GameObserver` objecten (observers). Hierdoor worden de observers automatisch op de hoogte gebracht van veranderingen in de toestand van het spel.

**Waarom het Observer Patroon nuttig is:**

- *Decoupling:* Verlaagt de directe afhankelijkheid tussen `Minesweeper` en `GameObserver`. Hierdoor blijft de code modulair en gemakkelijk uitbreidbaar.

- *Modulariteit:* Maakt het mogelijk om verschillende onderdelen van het systeem te laten reageren op veranderingen in de spelstatus zonder directe koppeling met `Minesweeper`.

- *Uitbreidbaarheid:* Nieuwe observers kunnen eenvoudig worden toegevoegd zonder de bestaande code van `Minesweeper` te wijzigen.

Het gebruik van het Observer Patroon maakt de code modulair, flexibel en gemakkelijk uitbreidbaar, wat onderhoud vereenvoudigt en de toevoeging van nieuwe functionaliteit mogelijk maakt.


## Decorator Pattern in Minesweeper Application

### Overzicht

Het **Decorator-patroon** is een structureel ontwerppatroon dat het mogelijk maakt om gedrag dynamisch toe te voegen aan individuele objecten, zonder het gedrag van andere objecten van dezelfde klasse te wijzigen. Dit patroon volgt het Open-Gesloten Beginsel, waarbij wordt benadrukt dat een klasse open moet zijn voor uitbreiding maar gesloten voor wijzigingen.

### Nut in Minesweeper

- **Dynamische Uitbreiding:**
    - Het Decorator-patroon maakt dynamische uitbreiding van functionaliteiten mogelijk zonder de bestaande code te wijzigen.
    - Flexibele toevoeging van nieuwe functies aan Minesweeper zonder de kernklasse aan te passen.

- **Open-Gesloten Beginsel:**
    - Behoudt het Open-Gesloten Beginsel, waardoor klassen open zijn voor uitbreiding maar gesloten voor directe aanpassingen.
    - Mogelijkheid om nieuwe functionaliteiten toe te voegen zonder de Minesweeper-klasse zelf te wijzigen.

- **Samenstelling Boven Erfenis:**
    - Het Decorator-patroon bevordert het gebruik van samenstelling boven erfelijkheid.
    - Modulaire en begrijpelijke code door focus op samenstellen van kleine, gefocuste klassen.

- **Scheiding van Zorgen:**
    - Decorators scheiden zorgen door specifieke aspecten van functionaliteit te behandelen.
    - In het Minesweeper-voorbeeld focust `LoggerDecorator` op loggen, en `GameObserver` op observeren van spelstaatveranderingen.

- **Herbruikbare en Combineerbare Componenten:**
    - Decorators zijn herbruikbare componenten die op verschillende manieren kunnen worden gecombineerd.
    - Eenvoudige toevoeging of wijziging van decorators voor diverse functionaliteiten zonder de kernklasse te wijzigen.

### Factory Design Pattern

Het **Factory Design Pattern** is een creational design pattern dat verantwoordelijk is voor het creëren van objecten zonder expliciet de klasse van het object te specificeren. In plaats daarvan wordt een interface of een abstracte klasse gebruikt voor het maken van objecten, en de exacte subklasse die wordt geïnstantieerd, wordt tijdens runtime bepaald.

#### Waarom Factory Design Pattern gebruiken?

- **Flexibiliteit**: Het biedt flexibiliteit doordat de concrete implementatie van een object kan worden gewijzigd zonder de code te wijzigen die het object gebruikt.

- **Encapsulatie**: Het scheidt de verantwoordelijkheid van het maken van objecten van het gebruik van objecten, wat leidt tot een betere encapsulatie.

- **Codehergebruik**: Het bevordert hergebruik van bestaande code, omdat het de implementatie verbergt achter een gemeenschappelijke interface.

- **Testbaarheid**: Het vergemakkelijkt het testen, omdat het gemakkelijk is om verschillende implementaties van een interface te vervangen door mock-objects tijdens tests.

