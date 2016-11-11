/*
 * This class is intended to hide access to other classes. 
 * In this class, we should be able to add machines, add pods, insert coffee
 * Allowing user to select machine they want pod to belong to. Something an admin would do ?
 */
package logic;
import java.util.ArrayList;
import product.Machine;
import product.Pod;
import product.Pod.CoffeeFlavor;
import product.Pod.PackSize;

public class Filler {
	private ArrayList<Machine> _machines = new ArrayList<>();
	private ArrayList<Pod> _pods = new ArrayList<>();
	
	public void AddMachine(String machineId, String machineType, String model, boolean isWaterLineCompatible)
	{
		Machine machine = new Machine(machineId, machineType, model, isWaterLineCompatible);
		_machines.add(machine);
	}
	
	public void AddPod(String podId, String podType, String size, String flavor)
	{
		PackSize packSize = GetPackSize(size);		
		CoffeeFlavor coffeeFlavor = GetCoffeeFlavor(flavor);
		Pod pod = new Pod(podId, podType, packSize, coffeeFlavor);
		_pods.add(pod);
		// after creating pod, insert it into correct machine
		for(Machine machine : _machines)
		{
			if(machine.isPodTypeCompatible(pod))
			{
				machine.AddPod(pod);
				break;
			}
		}
	}
	
	private PackSize GetPackSize(String size)
	{
		PackSize [] packSizes = PackSize.values();
		for(PackSize packSize : packSizes)
		{
			if(packSize.GetSizeValue().equals(size))
			{
				return packSize;
			}
		}
		return null;
	}
	
	private CoffeeFlavor GetCoffeeFlavor(String flavor)
	{
		CoffeeFlavor [] flavors = CoffeeFlavor.values();
		for(CoffeeFlavor coffeeFlavor : flavors)
		{
			if(coffeeFlavor.GetCoffeeFlavor().equals(flavor))
			{
				return coffeeFlavor;
			}
		}
		return null;
	}
	
	public ArrayList<Machine> GetMachines()
	{
		return _machines;
	}
	
	protected ArrayList<Pod> GetPods()
	{
		return _pods;
	}
}
