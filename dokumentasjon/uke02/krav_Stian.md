# Funksjonelle og ikke-funksjonelle krav for leietakere

## Funksjonelle krav:

* Leietaker skal kunne sende en leieforespørsel til en utleier ved å trykke på en knapp «send forespørsel».
  * Godtar utleier så åpner dette opp for videre funksjonalitet som f.eks. 
    * kommunisere med bileieren gjennom en chat-funksjon.
    * Kunne avbryte forespørsel hvis det oppstår uforutsette hendelser.

* Leietaker skal kunne angi leieperioden av en bil, ved å taste inn/ avkrysse dato for perioden.
* Leietaker skal kunne filtrerer søk blant utleiere med mest positive anmeldelser og kortest responstid.
* Leietaker skal kunne oppgi antatt kjøredistanse ved å taste inn den forventede kjørelengden i f.eks. et inputfelt. 
* Leietaker skal kunne filtrere biler basert på kategori (elektrisk, bensin, diesel), og biltype (SUV, sport, familiebil, van etc.) 
  gjennom avkrysningsbokser/nedtrekks-meny.
  * Gjelder også for egenskaper som manuell og automatgir.
* Leietaker skal kunne velge en prisgruppe, ved å taste prisen inn i et input-felt, eller velge blant prisgrupper i en nedtrekks-meny.
* Leietaker skal kunne betale for leieforholdet med Vipps eller bankkort.
* Leietaker skal kunne legge til en utleier i sin favorittliste, ved å besøke profilen til utleier og trykke på «legg til som favoritt» knappen.
* Leietaker skal kunne skrive «adresse, gatenavn eller sted» i applikasjonen og få tilgang til å se tilgjengelige biler i det bestemte området.
* Leietaker skal kunne se gjennom tidligere anmeldelser fra andre personer på brukeren til en utleier, hvis utleieren har noen tidligere anmeldelser.
  * Dette innebærer at leietaker også skal ha mulighet til å legge igjen en anmeldelse ved et leieforhold.
* Leietaker skal kunne stille spørsmål og få bistand hos et hjelpesenter for leietakere.
* Leietaker skal kunne ha mulighet til å se gjennom hva slags retningslinjer tjenesten har satt for leieforhold, slik at de kan gjøre seg kjent med kravene som gjelder, f.eks:
  * Fylling av drivstoff
  * Rengjøringsregler etter bruk
  * Verifisering av bruker (f.eks registrering med BankID)
  * Depositum?
  * Hvilke forsikringer gjelder?
  * Rapportering ved skade?

## Ikke-funksjonelle krav:
* Applikasjonen må kunne være tilgjengelig til enhver tid.
* Applikasjonen må benytte en database som lagrer data kryptert.
* Applikasjonen skal kunne håndtere x antall samtidige forespørsler.
* Applikasjonen bør følge de 35 minstekravene satt i WCAG 2.0.
  * Dette er ikke et krav siden vi kun skal lage en prototype.
* Applikasjonen bør inneholde sikkerhetsmekanismer som to-faktor-autentisering.
  * Whitelist og blacklist validering
  * Unngå funksjonalitet som «forbli innlogget».
* Applikasjonen bør kunne skaleres ved behov etter økt antall brukere (cloud baserte servere).
* Applikasjonen bør følge personvernforordningen ved lagring av personopplysninger.





