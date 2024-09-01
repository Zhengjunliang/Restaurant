# Descrizione dell’Applicativo

Siamo lieti di presentarvi la nostra applicazione, una soluzione **innovativa** e **intuitiva** progettata per ottimizzare la gestione operativa dei ristoranti. Questo strumento offre ai proprietari un sistema integrato per semplificare le attività quotidiane, mentre i clienti possono usufruire di un'esperienza di ordinazione fluida e agevole.

## 🚀 Funzionalità Principali

### 🔒 1. Schermata di Login
Al primo avvio, gli utenti accedono a una schermata di login sicura, appositamente concepita per garantire la protezione dei dati personali. Le funzionalità dell'applicazione sono differenziate in base al profilo dell'utente:
- **Amministratore:** Accesso completo a tutte le funzionalità di gestione del ristorante.
- **Cliente:** Accesso rapido e semplificato per la consultazione del menu e l’effettuazione degli ordini.

### 🛠️ 2. Funzionalità per l’Amministratore
L’applicazione offre agli amministratori un set di strumenti avanzati per una gestione efficiente del ristorante:
- **Gestione del Personale:** Possibilità di aggiungere, modificare o rimuovere i dipendenti, con assegnazione di ruoli specifici e monitoraggio delle attività.
- **Gestione del Menu:** Creazione e aggiornamento del menu con descrizioni dettagliate dei piatti, prezzi e offerte promozionali stagionali.
- **Monitoraggio degli Ordini:** Visualizzazione in tempo reale degli ordini ricevuti, gestione dello stato degli ordini e analisi delle statistiche di vendita per migliorare il servizio.

### 💻 3. Funzionalità per il Cliente
L'interfaccia dedicata ai clienti è progettata per garantire un'interazione semplice ed efficiente:
- **Consultazione del Menu:** Esplorazione del menu, visualizzazione dei dettagli dei piatti e selezione degli elementi desiderati.
- **Processo di Pagamento:** Esecuzione del pagamento con diverse opzioni disponibili, tra cui contanti, carte di credito o buoni pasto, offrendo flessibilità e comodità.

## 🛠️ Tecnologie Utilizzate
L’applicazione è stata sviluppata utilizzando le seguenti tecnologie per garantire una soluzione robusta e altamente performante:
- **JavaFX:** Per la creazione di interfacce grafiche fluide e reattive.
- **MySQL:** Per la gestione sicura e centralizzata dei dati.
- **JUnit5:** Per assicurare un testing rigoroso e affidabile.
- **JDBC:** Per una connessione efficiente al database.
- **Maven:** Per una gestione semplificata delle dipendenze e del ciclo di vita del progetto.

---

## 📄 Installazione e Configurazione

1. **Clona il repository:**
   ```bash
   git clone https://github.com/tuo-utente/tuo-progetto.git

2. **Importa il progetto in un IDE compatibile con Maven.**
   
3. **Configura il database MySQL:**
   - Importa lo schema `database.sql` in MySQL.
   - Aggiorna il file `application.properties` con le tue credenziali di accesso al database.

4. **Esegui il progetto:**
   ```bash
   mvn clean install
   mvn exec:java
