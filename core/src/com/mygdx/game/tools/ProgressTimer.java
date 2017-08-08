package com.mygdx.game.tools;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by ttwings on 2017/7/22.
 */
public class ProgressTimer {
    public static final int DIRECTION_CLOCKWISE = -1;
    public static final int DIRECTION_COUNTERCLOCKWISE = 1;
    private int MAX_VERTICE;
    private int MIN_VERTICE;
    private Texture texture;
    private Mesh mesh;
    private int iVertice;
    private float r;
    private float fCenterX;
    private float fCenterY;
    private int iDirection;
    public ProgressTimer(Texture texture, float centerX, float centerY, float r, int maxVertice, int minVertice, int direction) {
        fCenterX = centerX;
        fCenterY = centerY;
        this.r = r;
        MAX_VERTICE = maxVertice - minVertice;
        MIN_VERTICE = minVertice;
        this.texture = texture;
        iDirection = direction;
    }
    public void draw(float progress) {
        createMesh(progress);
        texture.bind();
//        g20 这个方法需要 shader
        mesh.render(SpriteBatch.createDefaultShader(),GL20.GL_TRIANGLE_FAN);
    }


    private void createMesh(float progress) {
        iVertice = (int) (MAX_VERTICE * progress) + MIN_VERTICE;
        double fTotalAngle = progress * 2 * Math.PI;
        double fPerAngle = fTotalAngle / (iVertice -2);
        if (mesh != null)
            mesh.dispose();
        mesh = new Mesh(true, iVertice * 3, iVertice,
                new VertexAttribute(VertexAttributes.Usage.Position, 3, "point"),

                new VertexAttribute(VertexAttributes.Usage.TextureCoordinates, 2,"texCoords"));
        float[] vertices = new float[iVertice * 5];
        short[] indexs = new short[iVertice];
        vertices[0] = fCenterX;
        vertices[1] = fCenterY;
        vertices[2] = 0;
        vertices[3] = 0.5f;
        vertices[4] = 0.5f;
        indexs[0] = 0;
        for (int i = 1; i < iVertice; i++) {
            float fAngle = (float) (iDirection * fPerAngle * (i-1));
            indexs[i] = (short) i;
            for (int j = 0; j < 5; j++) {
                int iIndex = i * 5 + j;
                switch (j)
                {
                    case 0:
                        vertices[iIndex] = (float) (fCenterX + r * Math.cos(fAngle));
                        break;
                    case 1:
                        vertices[iIndex] = (float) (fCenterY + r * Math.sin(fAngle));
                        break;
                    case 2:
                        vertices[iIndex] = 0;
                        break;
                    case 3:
                        vertices[iIndex] = (float) ((Math.cos(fAngle) + 1) / 2);
                        break;
                    case 4:
                        vertices[iIndex] = (float) (((-Math.sin(fAngle)) + 1) / 2);
                        break;
                }
            }
        }
        mesh.setVertices(vertices);
        mesh.setIndices(indexs);
    }
}
