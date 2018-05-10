# MixerChat-Bot

Mixer.com のライブチャットを Discord に送信する物です。

Java 8 で開発

# 使い方 (English is under)
1, [Releases](https://github.com/SimplyRin/MixerChat-Bot/releases) より `MixerChat-Bot-vX.X-SNAPSHOT.jar` をダウンロード

2, ダウンロードしたファイルをコンソールで実行 `java -jar MixerChat-Bot-vX.X-SNAPSHOT.jar`

3, 一度プログラムを停止し、`config.yml` を編集

```Yaml
Discord:
  Token: TOKEN # Bot トークンを貼り付け
  Guild: GUILD # Discord サーバーの ID を貼り付け
  Channel-ID: CHANNEL-ID # チャットを送信したいチャンネル ID を貼り付け
  Game: GAME # Bot プレイゲーム名を指定
Mixer:
  Token: TOKEN # Mixer API トークンを貼り付け
```

4, 編集後起動し、動作するかチェックしてみてください。

# Usage
1, Download `MixerChat-Bot-vX.X-SNAPSHOT.jar` from [Releases](https://github.com/SimplyRin/MixerChat-Bot/releases)

2, Run the downloaded file on the console `java -jar MixerChat-Bot-vX.X-SNAPSHOT.jar`

3, Stop the program once and edit `config.yml`

```Yaml
Discord:
  Token: TOKEN # Paste Bot token
  Guild: GUILD # Paste Discord server id
  Channel-ID: CHANNEL-ID # Paste server text channel id.
  Game: GAME # Specify Bot play game name
Mixer:
  Token: TOKEN # Paste Mixer API
```

4, Please start after editing and check if it works.

# 問題の報告
[Issues](https://github.com/SimplyRin/MixerChat-Bot/issues) または [Discord Server](https://discord.gg/9vEecBu) にて報告をお願い致します。

# ライセンス
[GPLv3](https://github.com/SimplyRin/MixerChat-Bot/blob/master/LICENSE.md) に基づき、作成/配布しています。

# 使用しているライブラリ
- [JDA](https://github.com/DV8FromTheWorld/JDA) v3.6.0_362
- [Mixer-API](https://maven.mixer.com/content/repositories/snapshots/) v6.0.0-SNAPSHOT
- [BungeeCord-Config](https://github.com/SpigotMC/BungeeCord/tree/master/config) v1.12-SNAPSHOT
