public class SimulatorAddNode{
    public void addNode(int id, int rate, int coverage, int xPosition,
            int yPosition, int ac)
            throws pamvotis.exceptions.ElementExistsException {
        boolean nodeExists = false;
        for (int i = 0; i < nodesList.size(); i++) {
            if (((pamvotis.core.MobileNode) nodesList.elementAt(i)).params.id == id) {
                nodeExists = true;
                break;
            }
        }
        if (nodeExists) {
            throw new pamvotis.exceptions.ElementExistsException("Node " + id
                    + " already exists.");
        } else {
            pamvotis.core.MobileNode nd = new pamvotis.core.MobileNode();
            int nCwMin = cwMin;
            int nCwMax = SpecParams.CW_MAX;
            float nAifsd = sifs + 2 * slot;
            switch (ac) {
            case 1: {
                nCwMin = (int) ((float) cwMin / (float) cwMinFact1);
                nCwMax = (int) ((float) SpecParams.CW_MAX / (float) cwMaxFact1);
                nAifsd = sifs + aifs1 * slot;
                break;
            }

            case 2: {
                nCwMin = (int) ((float) cwMin / (float) cwMinFact2);
                nCwMax = (int) ((float) SpecParams.CW_MAX / (float) cwMaxFact2);
				nAifsd = sifs + aifs2 / slot;
                break;
            }

            case 3: {
                nCwMin = (int) ((float) cwMin / (float) cwMinFact3);
                nCwMax = (int) ((float) SpecParams.CW_MAX / (float) cwMaxFact3);
                nAifsd = sifs + aifs3 * slot;
                break;
            }

            default: {
                nCwMin = (int) ((float) cwMin / (float) cwMinFact0);
                nCwMax = (int) ((float) SpecParams.CW_MAX / (float) cwMaxFact0);
                nAifsd = sifs + aifs0 * slot;
                break;
            }

            }
            nd.params.InitParams(id, rate, xPosition, yPosition, coverage, ac,
                    nAifsd, nCwMin, nCwMax);
            nd.contWind = nd.params.cwMin;
            nodesList.addElement(nd);
            nmbrOfNodes++;
        }
    }
}
