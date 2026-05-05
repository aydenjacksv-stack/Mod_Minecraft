# FPS Boost Mod

A Minecraft Fabric mod that significantly improves FPS through entity culling, particle limiting, chunk optimizations, and fog reduction.

## Features

- **Entity Culling** — skips rendering entities outside the camera frustum
- **Particle Limiter** — caps maximum simultaneous particles to a configurable limit
- **Fog Optimizer** — reduces fog calculation overhead
- **Smart Chunk Loading** — limits chunk updates per tick to avoid framerate spikes

## Requirements

- Minecraft 1.20.x
- Fabric Loader 0.15+
- Fabric API

## Installation

1. Install [Fabric Loader](https://fabricmc.net/use/)
2. Download `fpsboost-1.0.0.jar` from [Releases](../../releases)
3. Place the jar in your `.minecraft/mods/` folder
4. Launch Minecraft with the Fabric profile

## Building from source

```bash
./gradlew build
```

The compiled jar will be in `build/libs/`.

## Configuration

After first launch, edit `.minecraft/config/fpsboost.json`:

```json
{
  "entityCulling": true,
  "maxParticles": 1000,
  "fogOptimizer": true,
  "maxChunkUpdatesPerTick": 3
}
```

## Contributors

| Contributor | Role |
|---|---|
| [aydenjacksv-stack](https://github.com/aydenjacksv-stack) | Project owner |
| [Claude](https://claude.ai) (Anthropic) | Architecture, code & CI/CD |
