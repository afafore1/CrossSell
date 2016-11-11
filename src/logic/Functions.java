package logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import product.Machine;
import product.Pod;
import product.Pod.PackSize;

public class Functions {
	Filler _filler = new Filler();
	public void ParseFile(String filename) throws FileNotFoundException, IOException
	{
		try(BufferedReader br = new BufferedReader(new FileReader(filename)))
		{
			String line = "";
			while((line = br.readLine()) != null)
			{
				String id = line.substring(0, line.indexOf("–")).trim(); // this is bad, what if structure changes ?
				line = line.substring(line.indexOf("–") + 2, line.length()); // don't like this, better way ?
				if(line.contains("machine"))
				{
					String [] machineArray = line.split(",");
					if(machineArray.length == 3)
					{
						if(machineArray[2].trim().equals("water line compatible"))
						{
							_filler.AddMachine(id, machineArray[0], machineArray[1], true);
						}
					}
					else
					{
						_filler.AddMachine(id, machineArray[0], machineArray[1], false);
					}
				}
				else if(line.contains("pod"))
				{
					String [] podArray = line.split(",");
					String podType = podArray[0].trim();
					String size = podArray[1].trim();
					String flavor = podArray[2].trim();
					_filler.AddPod(id, podType, size, flavor);
				}
			}
		}
	}
	
	private ArrayList<Machine> GetAllMachineType(String machineType)
	{
		ArrayList<Machine> machinesToReturn = new ArrayList<>();
		ArrayList<Machine> allMachines = _filler.GetMachines();
		switch (machineType)
		{
		case "large machine":
			for(Machine machine : allMachines)
			{
				if(machine.GetMachineType().equals("large machine"))
				{
					machinesToReturn.add(machine);
				}
			}
			break;
		case "small machine":
			for(Machine machine : allMachines)
			{
				if(machine.GetMachineType().equals("small machine"))
				{
					machinesToReturn.add(machine);
				}
			}
			break;
		case "espresso machine":
			for(Machine machine : allMachines)
			{
				if(machine.GetMachineType().equals("espresso machine"))
				{
					machinesToReturn.add(machine);
				}
			}
			break;
		}
		return machinesToReturn;
	}
	
	private ArrayList<Pod> GetAllPodType(String podType)
	{
		ArrayList<Pod> podsToReturn = new ArrayList<>();
		ArrayList<Pod> allMachines = _filler.GetPods();
		switch (podType)
		{
		case "large":
			for(Pod pod : allMachines)
			{
				if(pod.GetPodType().equals("large coffee pod"))
				{
					podsToReturn.add(pod);
				}
			}
			break;
		case "small":
			for(Pod pod : allMachines)
			{
				if(pod.GetPodType().equals("small coffee pod"))
				{
					podsToReturn.add(pod);
				}
			}
			break;
		case "espresso":
			for(Pod pod : allMachines)
			{
				if(pod.GetPodType().equals("espresso pod"))
				{
					podsToReturn.add(pod);
				}
			}
			break;
		}
		return podsToReturn;
	}
	
	public ArrayList<String> GetAllMachineOfType(String machineType)
	{
		ArrayList<Machine> machines = GetAllMachineType(machineType);
		ArrayList<String> machineIds = new ArrayList<>();
		for(Machine machine : machines)
		{
			machineIds.add(machine.GetMachineId());
		}
		return machineIds;
	}
	
	public ArrayList<String> GetCrossSellForMachine(String machineType, String flavorLevel)
	{
		ArrayList<String> machineIds = new ArrayList<>();
		ArrayList<Machine> machineTypes = GetAllMachineType(machineType);
		ArrayList<Integer> podSizes = new ArrayList<>();
		int smallest = 0;
		for(Machine machine : machineTypes)
		{
			HashSet<Pod> machinePods = machine.GetPods();
			for(Pod pod : machinePods)
			{
				PackSize size = pod.GetSize();
				String [] sizeArray = size.GetSizeValue().split(" ");
				int sizeinArr = Integer.parseInt(sizeArray[0]);
				podSizes.add(sizeinArr);
			}
			Collections.sort(podSizes);
			smallest = podSizes.get(0);
			for(Pod pod : machinePods)
			{
				PackSize size = pod.GetSize();
				String [] sizeArray = size.GetSizeValue().split(" ");
				switch (flavorLevel)
				{
				case "smallest":
					if(Integer.parseInt(sizeArray[0]) == smallest)
					{
						machineIds.add(pod.GetPodId());
					}
					break;
				}
			}
		}
		return machineIds;
	}
	
	public HashSet<String> GetCrossSellForGeneral(String flavor)
	{
		HashSet<String> podIds = new HashSet<>();
		ArrayList<Pod> pods = _filler.GetPods();
		for(Pod pod : pods)
		{
			String [] podTypes = pod.GetPodType().split(" ");
			String podType = podTypes[0];
			HashSet<String> podList = new HashSet<>(GetAllChoices(podType, flavor));
			podIds.addAll(podList);
		}
		return podIds;
	}
	
	public ArrayList<String> GetAllChoices(String podType, String flavor)
	{
		ArrayList<String> podIds = new ArrayList<>();
		ArrayList<Pod> podTypes = GetAllPodType(podType);
		for(Pod pod : podTypes)
		{
			if(pod.GetFlavor().equals(flavor))
			{
				podIds.add(pod.GetPodId());
			}
		}
		return podIds;
	}
}
