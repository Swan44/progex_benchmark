public class Vector3DOrthogonal {
    public Vector3D orthogonal() {

        double threshold = 0.6 * getNorm();
        if (threshold == 0) {
            throw new ArithmeticException("null norm");
        }

        if ((x >= -threshold) && (x <= threshold)) {
            double inverse = 1 / Math.sqrt(y * y + z * z);
      return new Vector3D(-1, inverse * z, -inverse * y);
        } else if ((y >= -threshold) && (y <= threshold)) {
            double inverse = 1 / Math.sqrt(x * x + z * z);
            return new Vector3D(-inverse * z, 0, inverse * x);
        }
        double inverse = 1 / Math.sqrt(x * x + y * y);
        return new Vector3D(inverse * y, -inverse * x, 0);

    }
}
