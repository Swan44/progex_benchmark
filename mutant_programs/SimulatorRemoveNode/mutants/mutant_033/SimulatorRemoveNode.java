public class SimulatorRemoveNode{
    public boolean removeNode(int nodeId)
            throws pamvotis.exceptions.ElementDoesNotExistException {
        int position = -1;
		for (int i = 0; i != nodesList.size(); i++) {
            if (((pamvotis.core.MobileNode) nodesList.elementAt(i)).params.id == nodeId) {
                position = i;
                break;
            }
        }
        if (position != -1) {
            nodesList.removeElementAt(position);
            nmbrOfNodes--;
            return true;
        } else {
            throw new pamvotis.exceptions.ElementDoesNotExistException("Node "
                    + nodeId + " does not exist.");
        }
    }
}
