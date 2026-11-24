public class ElectroMove {

	private static final int MAX_V = 8;
	private static final int MAX_PC = 5;

	public static void main(String[] args) {

		// Declaració de les variables
		Vehicle[] vehicles = new Vehicle[MAX_V];
		PuntCarrega[] puntsCarrega = new PuntCarrega[MAX_PC];
		int opcio = 0;

		// Inicialització array de vehicles
		vehicles[0] = new Vehicle("1234ABC", "Tesla", "Model 3", 513,
				new Coordenades(41.3851, 2.1734));
		vehicles[1] = new Vehicle("2345BCD", "Hyundai", "Kona Electric", 484,
				new Coordenades(41.6176, 0.6200));
		vehicles[2] = new Vehicle("3456CDE", "Kia", "EV3", 605,
				new Coordenades(41.9794, 2.8214));
		vehicles[3] = new Vehicle("4567DEF", "Renault", "Megane E-Tech", 468,
				new Coordenades(41.1189, 1.2445));
		vehicles[4] = new Vehicle("5678EFG", "Peugeot", "E-208", 400,
				new Coordenades(41.5463, 2.1086));
		vehicles[5] = new Vehicle("6789FGH", "Hyundai", "IONIQ 5", 540,
				new Coordenades(41.9301, 2.2549));
		vehicles[6] = null;
		vehicles[7] = null;

		// Inicialització array de punts de càrrega
		puntsCarrega[0] = new PuntCarrega("BCN-001", new Coordenades(41.4750, 2.1800),
				150.0, PuntCarrega.TYPE2);
		puntsCarrega[1] = new PuntCarrega("BCN-002", new Coordenades(41.3000, 2.0500),
				150.0, PuntCarrega.CHADEMO);
		puntsCarrega[2] = new PuntCarrega("GIR-001", new Coordenades(41.9900, 2.8000),
				100.0, PuntCarrega.CHADEMO);
		puntsCarrega[3] = new PuntCarrega("TAR-001", new Coordenades(41.1200, 1.2200),
				100.0, PuntCarrega.CCS);
		puntsCarrega[4] = new PuntCarrega("LLE-001", new Coordenades(41.6300, 0.6500),
				50.0, PuntCarrega.TYPE2);

		do {
            // Mostrem el menú tal com es demana
			System.out.println("\n\t\tGESTIÓ MOBILITAT ELÈCTRICA \n");
			System.out.println("\n" +
					"\t1. Mostrar vehicles\n" +
					"\t2. Mostrar punts de càrrega\n" +
					"\t3. Posar en càrrega vehicle\n" +
					"\t4. Donar d'alta vehicle\n" +
					"\t5. Sortir\n");
            // Demanem i llegim la opció escollida
            System.out.print("               Escull una opció: ");
            // Limitem el valor màxim i mínim de la variable opció
			opcio = InputUtils.readInt(1, 5);
			System.out.println();
			switch (opcio) {
				case 1:
                    // Recorrem l'array de vehicles amb el metode de mostrarArray
                    mostrarArray(vehicles);
					break;
				case 2:
                    // Recorrem l'array de puntsCarrega amb el metode de mostrarArray
					mostrarArray(puntsCarrega);
					break;
				case 3:
                    // Demanem la matrícula del vehicle
					System.out.print("Introdueix matrícula vehicle: ");
					String matriculaTest = InputUtils.readLine();
                    // Comprobem si existeix la matrícula
					Vehicle vehicleSeleccionat = comprobaMatricula(matriculaTest, vehicles);
                    // Establim com valor per defecte null per al punt de càrrega disponible
					PuntCarrega puntCarregaDisponible = null;
                    // Si no coincideix la matricula introduida mostrarem el missatge
					if (vehicleSeleccionat == null) {
						System.out.println("\n La matrícula del vehicle no existeix. \n");
                    // Si la matricula coincideix, però el vehicle ja està carregant mostrarem el missatge
					} else if (vehicleCarregant(vehicleSeleccionat)) {
						System.out.println("\n El vehicle ja està carregant. \n");
                    // Si no hi ha cap punt de carrega disponible mostrarem el missatge
					} else if ((puntCarregaDisponible = puntCarregaLliureProper(vehicleSeleccionat,
							puntsCarrega)) == null) {
						System.out.println("\n No hi ha cap punt de càrrega lliure. \n");
                    // Una vegada fetes les comprobacions, cambiem els estats demanats i mostrem el missatge
					} else {
						vehicleSeleccionat.setEstat(Vehicle.EN_CARREGA);
						puntCarregaDisponible.setEstat(PuntCarrega.OCUPAT);
						System.out.println("\n El vehicle s'està carregant al punt de càrrega "
								+ puntCarregaDisponible.getIdentificador() + " \n");
					}
					break;
				case 4:
					// Comprovar si hi ha espai a l’array
					int posicioLliure = buscarPosicioLliure(vehicles);
					if (posicioLliure == -1 ) { // Si no hi ha cap posició lliure mostrem el missatge
						System.out.println("No es poden registrar més vehicles.");
                    } else { // Si hi ha espai dins de l'array
                        // Demanar la matrícula a l’usuari
                        System.out.print("Matrícula: ");
                        String matriculaTestNou = InputUtils.readLine();
                        // Comprovem si existeix la matricula
                        Vehicle vehicleNouTest = comprobaMatricula(matriculaTestNou, vehicles);
                        if (vehicleNouTest == null) { // Si no existeix
                            // Demanar la resta de dades del vehicle a l’usuari
                            donarAltaVehicle(vehicles, matriculaTestNou);
                            System.out.println("\n" + "El vehicle ha estat creat." + "\n");
                        } else { // Si existeix
                            // Mostrar un missatge d’error
                            System.out.println("\n" + "Aquesta matrícula ja existeix." +"\n");
                        }
					}
                    break;
				case 5:
                    // Mostrem el missatge de sortida, el tancament de l'scaner es fa després de terminar el while
					System.out.println("\n" + "\nSortint del programa...");
			}
		} while (opcio != 5);
		InputUtils.close();
	}

    /* 
    * Metode per mostrar un array d'objectes
    * Unifiquem els punts 1 i 2
    * No dupliquem codi
    */
    public static void mostrarArray(Object[] array) {
        // Recorrem l'array
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) { // Evitem mostrar les posicions buides
                System.out.println(array[i].toString());
            }
        }
        System.out.print("\n");
    }

	/*
	 * Metode per comprobar si existeix la matricula
	 * Si existeix retorna l'objecte del vehicle
	 * Si no existeix retorna null
     * Retornem l'objecte del vehicle
	 */
	public static Vehicle comprobaMatricula(String matricula, Vehicle[] vehicles) {
		// Establim com a valor per defecte null per controlar el que es retorna en cas de no trobar el Vehicle
        Vehicle vehicleMatricula = null;
		// Recorrem l'array
		for (int i = 0; i < vehicles.length; i++) {
			// Si la posicio a l'array no es null compararem la matricula
			if (vehicles[i] != null) {
				if (vehicles[i].getMatricula().equals(matricula)) {
                    // Si la matricula existeix retornem l'objecte del vehicle
					vehicleMatricula = vehicles[i];
				}
			}
		}
		return vehicleMatricula;
	}

	/*
	 * Metode per comprobar si un vehicle ja està carregant
	 * Ho posem com a boolea
	 * true es carregant
	 * false es no està carregant
	 * Retornem el booleà
	 */
	public static boolean vehicleCarregant(Vehicle vehicle) {
		// Per defecte es true
		boolean vehicleCarregant = true;
		// Si no te l'estat 3 posem false
		if (vehicle.getEstat() != 3) {
			vehicleCarregant = false;
		}
		return vehicleCarregant;
	}

	/*
	 * Metode per trobar el punts de carrega lliures
	 * Ho posem com a boolea
	 * true es lliure
	 * false es no lliure, que potser OCUPAT o AVARIAT
	 * Retornem el booleà
	 */
	public static boolean puntCarregaLliure(PuntCarrega puntCarrega) {
		// Per defecte es true
		boolean puntLliure = true;
		// Si no te l'estat 1 posem false
		if (puntCarrega.getEstat() != 1) {
			puntLliure = false;
		}
		return puntLliure;
	}

	/*
	 * Metode per buscar el punt de carrega lliure més proper
	 * Retornem el punt de carrega més proper
	 */
	public static PuntCarrega puntCarregaLliureProper(Vehicle vehicleSeleccionat, PuntCarrega puntsCarrega[]) {
		// Obtenim i assignem les coordenades del vehicle
		Coordenades coordenadesVehicle = vehicleSeleccionat.getUbicacio();
        // Posem per defecte el valor null
		PuntCarrega puntLliureMesProper = null;
		// Inicialitzem la distancia amb el valor màxim possible
		double minimDistancia = Double.MAX_VALUE;
		// Ara buscarem el punt més proper, per això hem posat abans el valor més alt
		for (int i = 0; i < puntsCarrega.length; i++) {
			PuntCarrega puntActual = puntsCarrega[i];
			// Comprovem que el punt de carrega no es null (es a dir la posició dins l'array conte un punt de carrega) i si està lliure
			if (puntActual != null && puntCarregaLliure(puntActual)) {
				// Si el valor es valid dins la posició
                // Calculem la distancia entre el punt de carrega i el vehicle
				double distancia = puntActual.getUbicacio().distancia(coordenadesVehicle);
				// Si trobem una distància menor que la mínima actual, la guardem
				// Recorrerem tot l'array i guardarem la més petita
				if (distancia < minimDistancia) {
					minimDistancia = distancia;
                    // Mentre recorrem l'array guardarem el punt de càrrega que estigui més proper
					puntLliureMesProper = puntActual;
				}
			}
		}
		return puntLliureMesProper;
	}
    
    /*
	* Metode per buscar una posicio lliure dins d'un array d'objectes
    * Retornem un enter que serà la posició
    */
	public static int buscarPosicioLliure(Object[] array) {
		// Per defecte posem el valor -1 com a posició
        int posicioLliure = -1;
        // Recorrem tot l'array fins trobar la primera posició lliure, per això posem el break
        for (int i = 0; i < array.length; i++) {
            // La posició lliure serà un null
			if (array[i] == null) {
				posicioLliure = i;
                break;
			}
		}
		return posicioLliure;
	}

    /*
    * Metode per donar d'alta un vehicle
    * Només dona d'alta un vehicle nou al array, no fa cap comprobació ni retorna cap valor
    * El filtratge de les opcions es fa al case corresponent
    */
    public static void donarAltaVehicle(Vehicle[] vehicles, String matricula) {
        // Utilitzem el metode buscarPosicioLliure per trobar la primera posició lliure
        int posicioLliure = buscarPosicioLliure(vehicles);
        // La matricula ja la hem demanada al case i només la assignem
        String matriculaNou = matricula;
        // Demanarem la resta de dades del vehicle a l’usuari
        System.out.print("Marca: ");
        String marcaNou = InputUtils.readLine();
        System.out.print("Model: ");
        String modelNou = InputUtils.readLine();
        System.out.print("Autonomia (en km): ");
        int autonomiaNou = InputUtils.readInt();
        System.out.println("Ubicació:");
        System.out.print("\tLatitud: ");
        double latitudNou = InputUtils.readReal(Coordenades.MIN_LAT, Coordenades.MAX_LAT);
         System.out.print("\tLongitud: ");
        double longitudNou = InputUtils.readReal(Coordenades.MIN_LONG, Coordenades.MAX_LONG);
        // Creem un nou objecte Coordenades
        Coordenades coordenadaNou = new Coordenades(latitudNou, longitudNou);
        // Creem el vehicle i el guardem a la primera posició lliure de l’array
        vehicles[posicioLliure] = new Vehicle(matriculaNou, marcaNou, modelNou, autonomiaNou, coordenadaNou);
    }

}
