package de.hrw.wi.service;

import java.util.Set;

import de.hrw.wi.business.Product;
import de.hrw.wi.business.StorageBin;

/**
 * Ein WarehoseManagementService stellt Funktionen zum Einlagern von Produkten,
 * Auslagern von Produkten etc. bereit. Er erh�lt seine Daten von den einzelnen
 * L�gern (Warehouse), gibt aber nie direkt Zugriff auf L�ger sondern maximal
 * deren jeweilige Nummer.
 * 
 * @author andriesc
 *
 */
public interface WarehouseManagementServiceInterface {
	/**
	 * Erzeugt die angegebene Anzahl von L�gern, alle mit der gleichen Menge an
	 * F�chern und Gr��e der einzelnen F�cher.
	 * 
	 * @param amount
	 *            Anzahl zu erzeugender L�ger
	 * @param numberOfBins
	 *            Anzahl der F�cher jeweils in den L�gern
	 * @param sizeOfEachBin
	 *            Gr��e der F�cher jeweils
	 */
	void createWarehouses(int amount, int numberOfBins, int sizeOfEachBin);

	/**
	 * @return Die Nummern der aktiven L�ger, egal ob in ihnen Produkte lagern
	 *         oder nicht.
	 */
	Set<Integer> getWarehouseNumbers();

	/**
	 * Legt das Lager mit der angegebenen Nummer still. Ein Lager kann nicht
	 * stillgelegt werden, wenn in ihm noch Gegenst�nde gelagert sind. Nur ein
	 * leeres Lager kann stillgelegt werden.
	 * 
	 * @param number
	 *            Nummer des stillzulegenden Lagers
	 * @return true, wenn das Lager erfolgreich stillgelegt werden konnte
	 */
	boolean removeWarehouse(int number);

	/**
	 * Schl�gt Lager und Fach vor:
	 * 
	 * <ul>
	 * <li>Existierendes Fach, wenn es das Produktschon gibt und der Platz im
	 * Fach reicht
	 * 
	 * <li>sonst Neues Fach im selben Lager, wenn das Lager noch Platz hat,
	 * 
	 * <li>sonst neues Fach in neuem Lager, wenn es noch ein Lager mit Platz
	 * gibt
	 * 
	 * <li>Sonst Fehler
	 * </ul>
	 * 
	 * @param p produkt
	 * @param amount amount
	 * @return StorageBin der das angegebene Produkt in der angegebenen Anzahl
	 *         aufnehmen kann, sonst null.
	 */
	StorageBin proposeStorageBinFor(Product p, int amount);

	/**
	 * Lagert angegebene Menge des angegebenen Produktes ein. Kann das Produkt
	 * in der angegebenen Anzahl nicht eingelagert werden, wird gar nichts
	 * eingelagert.
	 * 
	 * @param p p
	 * @param amount amount
	 * @return true, wenn alles eingelagert werden konnte, sonst false.
	 */
	boolean placeIntoStock(Product p, int amount);

	/**
	 * @param p p
	 * @return Gibt zur�ck, welche St�ckzahl vom Produkt p in allen L�gern
	 *         insgesamt vorhanden ist.
	 */
	int availableStock(Product p);

	/**
	 * Liefert alle Lagerpl�tze f�r das angegebene Produkt zur�ck.
	 * 
	 * @param p p
	 * @return Eine Menge aller Lagerpl�tze f�r das angegebene Produkt
	 */
	Set<StorageBin> storageBinsFor(Product p);

	/**
	 * @return Gibt zur�ck, welche Produkte insgesamt in den L�gern vorhanden
	 *         sind, ohne die jeweilige St�ckzahl zu ber�cksichtigen
	 */
	Set<Product> availableStock();

	/**
	 * Vom angegebenen Produkt die angegebene Menge auslagern.
	 * 
	 * @param p
	 *            Das auszulagernde Produkt
	 * @param amount
	 *            Die auszulagernde Menge
	 * @return Die Lagerpl�tze, aus denen das Produkt ausgelagert wurde.
	 */
	Set<StorageBin> removeFromStock(Product p, int amount);
}
