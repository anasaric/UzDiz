# UzDiz
 Design Patterns project
 Developed as part of the 2023/2024 academic curriculum for the Design Patterns course.

# Package Delivery System

This project simulates a package delivery system where a company handles the delivery of various types of packages with specific delivery services. The system operates with multiple vehicles, each performing several deliveries during the working day, following a set of defined rules for handling packages, loading, delivery, and returning to the office.

## Overview

The system includes functionalities for:

- **Package types**: There are predefined types (A, B, C, D, E) and flexible size packages (X), each with its own weight and dimension restrictions.
- **Delivery services**: Different services are offered for packages, including standard, express, cash on delivery, and package return.
- **Working hours**: The system operates within specified working hours, and deliveries can continue even after the office hours if packages are not yet delivered.
- **Vehicle management**: Vehicles are used to load and deliver packages, ensuring weight and space limits are not exceeded.
- **Package delivery routes**: Routes are calculated using GPS data, and deliveries are optimized based on urgency and proximity to the destination.

## Features

- **Package and vehicle management**: The system supports loading and delivering packages based on their type, weight, dimensions, and service required.
- **GPS-based routing**: For each delivery, the system calculates the distance and time required for vehicle travel from the office to the recipient’s address.
- **Virtual clock**: The system operates with a virtual clock that tracks time during the execution of the delivery tasks.
- **Command-line interaction**: Users can interact with the system by executing commands, such as viewing the status of packages, vehicles, and deliveries, as well as simulating the passage of time.
- **Data persistence**: All data is stored in CSV files and uses the Croatian date format (`dd.mm.yyyy hh:mm:ss`).

## System Design

The system uses multiple design patterns to ensure scalability and maintainability:

1. **State Pattern**: The system defines different states for vehicles and transitions between those states as packages are loaded, delivered, or returned.
2. **Chain of Responsibility**: Commands in the system follow this pattern to process and handle different actions based on the state of the system.
3. **Visitor Pattern**: For handling reports and statistics about vehicles, packages, and delivery segments.
4. **Observer Pattern**: The system notifies senders and recipients when packages are loaded and delivered.
5. **Composite Pattern**: Shows hierarchical structure where regions can contain other regions (cities/towns), and each city/town can contain multiple streets.
6. **Decorator Pattern**: In the implementation of the IP command, additional functionality has been added using this pattern. Specifically, the ability to color the output of the IP command table in red.
7. **State Pattern**: Each vehicle is assigned areas based on its rank. Vehicles can be active, inactive, or faulty. Only active vehicles can load packages.
8. **Template Method**: We couldn't have implemented any of the patterns above without loading data from CSV files containing information about packages, package types, people, vehicles, and areas.

## Files

The system reads and writes data from several CSV files, formatted as follows:

### Package Types (`DZ_3_vrste.csv`)
- **Attributes**: `Oznaka, opis, visina, širina, dužina, maksimalna težina, cijena, cijena hitno, cijenaP, cijenaT`
  - Defines package types with dimensions and weight restrictions.
  
### Vehicle List (`DZ_3_vozila.csv`)
- **Attributes**: `Registracija, opis, kapacitet težine, kapacitet prostora, redoslijed, prosječna brzina, područja po rangu, status`
  - Lists vehicles with their registration, capacity, and area associations.

### Packages List (`DZ_3_paketi.csv`)
- **Attributes**: `Oznaka, vrijeme prijema, pošiljatelj, primatelj, vrsta paketa, visina, širina, dužina, težina, usluga dostave, iznos pouzeća`
  - Contains package details including dimensions, weight, delivery service, and recipient information.

### Streets List (`DZ_3_ulice.csv`)
- **Attributes**: `Id, naziv, gps_lat_1, gps_lon_1, gps_lat_2, gps_lon_2, najveći kućni broj`
  - Defines streets with GPS coordinates for delivery routing.

### Places List (`DZ_3_mjesta.csv`)
- **Attributes**: `Id, naziv, ulica`
  - Contains locations and their associated streets.

### People List (`DZ_3_osobe.csv`)
- **Attributes**: `Osoba, grad, ulica, kbr`
  - Defines senders and recipients, including individuals and companies.

### Areas List (`DZ_3_podrucja.csv`)
- **Attributes**: `Id, grad:ulica, grad:ulica, grad:*`
  - Contains area codes that map to specific city streets.

## Commands

The system can execute the following commands:

- **`IP`**: View the status of all packages at the current virtual time.
  
- **`VR hh`**: Advance the virtual time by `hh` hours.
  
- **`SV`**: View the status of all vehicles (e.g., total distance traveled, number of packages, capacity usage).
  
- **`VV vozilo`**: View the details of a specific vehicle's trips (start time, return time, distance, number of packages).
  
- **`VS vozilo`**: View the details of a specific vehicle’s delivery segments during the day.

- **`PP`**: View the hierarchical overview of areas, including places and the streets in those places.  
            This command outputs a table showing the areas, the places within those areas, and the streets in each place.

- **`PS vozilo [A | NI | NA]`**: Change the status of a vehicle at the current virtual time.  
  - `A` – Active  
  - `NI` – Not functioning  
  - `NA` – Not active  
  Example:  
  - **`PS VZ100PK NI`**: Sets the vehicle VZ100PK status to "Not functioning".

- **`PO 'reciever/sender' package [D | N]`**: Change the notification status for a sender or recipient of a package.  
  - `D` – Send notifications  
  - `N` – Do not send notifications  
  Example:  
  - **`PO 'Pero Kos' CROVZ0001 N`**: Disables notifications for the sender Pero Kos regarding the package CROVZ0001.

- **`SPV 'naziv'`**: Save the current state of the virtual clock, packages, and vehicles (without data serialization).  
  Example:  
  - **`SPV 'korak 1'`**: Saves the current state under the name "korak 1".

- **`PPV 'naziv'`**: Restore a previously saved state of the virtual clock, packages, and vehicles (without data deserialization).  
  Example:  
  - **`PPV 'korak 1'`**: Restores the saved state from "korak 1".

- **`Q`**: Terminate the program.  
  This command ends the program's execution.

## Setup and Usage

### Prerequisites
- Python 3.x
- Required libraries:
  - `csv`
  - `datetime`


