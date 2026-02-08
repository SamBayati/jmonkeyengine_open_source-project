
     */
    public void setHeight(List<Vector2f> xz, List<Float> height);

    /**
     * Raise/lower the height in one call (instead of getHeight then setHeight).
     * @param xzCoordinate world coordinate to adjust the terrain height
     * @param delta +- value to adjust the height by
     */
    public void adjustHeight(Vector2f xzCoordinate, float delta);

    /**
     * Raise/lower the height at many points. The two lists must be the same size.
     * Each xz coordinate entry matches to a height entry, 1 for 1. So the
     * first coordinate matches to the first height value, the last to the
     * last etc.
     * @param xz a list of coordinates where the height will be adjusted
     * @param height +- value to adjust the height by, that matches the xz coordinates
     */
    public void adjustHeight(List<Vector2f> xz, List<Float> height);

    /**
     * Get the heightmap of the entire terrain.
     * This can return null if that terrain object does not store the height data.
     * Infinite or "paged" terrains will not be able to support this, so use with caution.
     */
    public float[] getHeightMap();
    
    /**
     * This is calculated by the specific LOD algorithm.
     * A value of one means that the terrain is showing full detail.
     * The higher the value, the more the terrain has been generalized
     * and the less detailed it will be.
     */
    public int getMaxLod();

    /**
     * Lock or unlock the meshes of this terrain.
     * Locked meshes are un-editable but have better performance.
     * This should call the underlying getMesh().setStatic()/setDynamic() methods.
     * @param locked or unlocked
     */
    public void setLocked(boolean locked);

    /**
     * Pre-calculate entropy values.
     * Some terrain systems support entropy calculations to determine LOD
     * changes. Often these entropy calculations are expensive and can be
     * cached ahead of time. Use this method to do that.
     */
    public void generateEntropy(ProgressMonitor monitor);

    /**
     * Returns the material that this terrain uses.
     * If it uses many materials, just return the one you think is best.
     * For TerrainQuads this is sufficient. For TerrainGrid you want to call
     * getMaterial(Vector3f) instead.
     */
    public Material getMaterial();
    
    /**
     * Returns the material that this terrain uses.
     * Terrain can have different materials in different locations.
     * In general, the TerrainQuad will only have one material. But 
     * TerrainGrid will have a different material per tile.
     * 
     * It could be possible to pass in null for the location, some Terrain
     * implementations might just have the one material and not care where
     * you are looking. So implementations must handle null being supplied.
     * 
     * @param worldLocation the location, in world coordinates, of where 
     * we are interested in the underlying texture.
     */
