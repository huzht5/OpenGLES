#version 300 es
precision mediump float;

out vec4 FragColor;

void main() {
    FragColor = vec4(1.0f, 0.5f, 0.2f, 1.0f);
}


////另一种写法
//precision mediump float;
//
//void main() {
//    gl_FragColor = vec4(1.0f, 0.5f, 0.2f, 1.0f);
//}