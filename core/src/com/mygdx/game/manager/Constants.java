package com.mygdx.game.manager;

import com.badlogic.gdx.Input;

public class Constants {
    // 1280像素宽
    public static final int VIEWPORT_WIDTH = 1280;
    // 800像素高
    public static final int VIEWPORT_HEIGHT = 800;
    // FPS 帧数限制
    public static final int FPS = 30;
    // direction
    public static int DIR_N = 8;
    public static int DIR_NW = 7;
    public static int DIR_NE = 9;
    public static int DIR_S = 2;
    public static int DIR_SW = 1;
    public static int DIR_SE = 3;
    public static int DIR_W = 4;
    public static int DIR_E = 6;
    public static int DIR_C = 5;
    public static float stateTime = 0.0f;
    public static float framedur = 0.01f;
    // Key Mapinng 按键映射
    public static int KEY_U = Input.Keys.E;
    public static int KEY_D = Input.Keys.D;
    public static int KEY_L = Input.Keys.S;
    public static int KEY_R = Input.Keys.F;
    public static int KEY_A = Input.Keys.J;
    public static int KEY_B = Input.Keys.K;
    public static int KEY_X = Input.Keys.I;
    public static int KEY_Y = Input.Keys.L;
    public static int KEY_BACK = Input.Keys.G;
    public static int KEY_START = Input.Keys.H;
    public static int KEY_L1 = Input.Keys.R;
    public static int KEY_R1 = Input.Keys.U;
    public static int KEY_L2 = Input.Keys.W;
    public static int KEY_R2 = Input.Keys.O;

    public static final String vertexShader =
            "attribute vec4 a_position;  \n"
                    + "attribute vec4 a_color;  \n"
                    + "attribute vec2 a_texCoord0;  \n"
                    + "uniform mat4 u_projTrans;  \n"
                    + "varying vec4 v_color;  \n"
                    + "varying vec2 v_texCoords;  \n"
                    + "void main() {           \n"
                    + "   v_color = a_color;  \n"
                    + "   v_texCoords = a_texCoord0;  \n"
                    + "   gl_Position = u_projTrans * a_position;  \n"
                    + "}";

    public static final String fragmentShader =
            "uniform sampler2D u_texture;    \n"
                    + "uniform float hue ;         \n"   // 定义色相变量
                    + "uniform float saturation ;  \n"   // 定义饱和度变量
                    + "varying vec4 v_color;           \n"
                    + "varying vec2 v_texCoords;     \n"
                    + "void main( void )            \n"
                    + "{        \n"
                    + "    vec4 color = texture2D(u_texture, v_texCoords);   \n"
                    + "    float angle = hue * 3.14159265;    \n"
                    + "    float s = sin(angle), c = cos(angle);   \n"
                    + "    vec3 weights = (vec3(2.0 * c, -sqrt(3.0) * s - c, sqrt(3.0) * s - c) + 1.0) / 3.0;   \n"
                    + "    float len = length(color.rgb);    \n"
                    + "    color.rgb = vec3(dot(color.rgb, weights.xyz), dot(color.rgb, weights.zxy), dot(color.rgb, weights.yzx));  \n"
                    + "    float average = (color.r + color.g + color.b) / 3.0;    \n"
                    + "    if (saturation > 0.0) {    \n"
                    + "        color.rgb += (average - color.rgb) * (1.0 - 1.0 / (1.001 - saturation));\n"
                    + "    } else {    \n"
                    + "        color.rgb += (average - color.rgb) * (-saturation);   \n"
                    + "    }    \n"
                    + "    gl_FragColor = color;   \n"
                    + "}";
    public static final String black2alphaShader =
            "#ifdef GL_ES                                              \n" +
                    "precision lowp float;                                     \n" +
                    "#endif                                                    \n" +
                    "varying vec4 v_color;                             \n" +
                    "varying vec2 v_texCoords;                                  \n" +
                    "uniform sampler2D u_texture;                              \n" +
                    "void main()                                               \n" +
                    "{                                                         \n" +
                    "    float ratio=0.0;                                      \n" +
                    "    vec4 texColor = texture2D(u_texture, v_texCoords);     \n" +
                    "    ratio = texColor[0] > texColor[1]?(texColor[0] > texColor[2] ? texColor[0] : texColor[2]) :(texColor[1] > texColor[2]? texColor[1] : texColor[2]);                                        \n" +
                    "if (ratio != 0.0)                                            \n" +
                    "{                                                            \n" +
//                    "    texColor[0] = texColor[0] /  ratio;                      \n" +
//                    "    texColor[1] = texColor[1] /  ratio;                      \n" +
//                    "    texColor[2] = texColor[2] /  ratio;                      \n" +
//                    "    texColor[3] = ratio;                                     \n" +
                    "}                                                            \n" +
                    "else                                                         \n" +
                    "{                                                            \n" +
                    "    texColor[3] = 0.0;                                       \n" +
                    "}                                                            \n" +
                    "gl_FragColor = v_color*texColor;                     \n" +
                    "}";
}
