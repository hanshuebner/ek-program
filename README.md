# ExpertKeys EK-20 USB programming protocol

The ExpertKeys EK-20 is a programmable keyboard with 20 keys, each of
which can be freely programmed.  The vendor supplies a Windows program
to program the keys, but as a Mac user, I wanted a way to program it
directly.  This repository documents what I found out about the
programming protocol and contains some bits of code to do the
programming using Clojure.

The protocol of the EK-20 is USB HID based.  To program keys, feature
reports need to be send to the device using report ID 1.  Each
programming packet consists of 130 bytes:

0: Command (0x10 => press, 0x20 => release)
1: Key Number
2: Length of definition (0-127)
3-129: Definition ...

The definition is ASCII string with keys spelled out,
e.g. "<LCtrl>a".

The press and release definition are defined by two separate commands.

Special key names:

<**MyTab**>
<App>
<BackSpace>
<BassBoost>
<BassDown>
<BassUp>
<Beep>
<CR>
<Calculator>
<CapsLock>
<Delay100ms>
<Delay1s>
<Delete>
<DownArrow>
<End>
<Enter>
<Esc>
<F10>
<F11>
<F12>
<F13>
<F14>
<F15>
<F16>
<F17>
<F18>
<F19>
<F1>
<F20>
<F21>
<F22>
<F23>
<F24>
<F2>
<F3>
<F4>
<F5>
<F6>
<F7>
<F8>
<F9>
<Home>
<Insert>
<LAlt>
<LCtrl>
<LGUI>
<LShift>
<Layer2>
<Layer3>
<Layer4>
<Layer5>
<LayerLock>
<LeftArrow>
<Loudness>
<Mail>
<MediaSelect>
<Mute>
<MyComputor>
<New>
<NumLock>
<Pad*>
<Pad+>
<Pad->
<Pad.>
<Pad/>
<Pad0>
<Pad1>
<Pad2>
<Pad3>
<Pad4>
<Pad5>
<Pad6>
<Pad7>
<Pad8>
<Pad9>
<PadEnter>
<PageDown>
<PageUp>
<PauseBreak>
<Play/Pause>
<Power>
<PrintScreenSysRq>
<RAlt>
<RCtrl>
<RGUI>
<RH>
<RL>
<RM>
<RShift>
<RightArrow>
<Ro>
<ScanNextTrack>
<ScanPreTrack>
<ScrollLock>
<Sleep>
<SpaceBar>
<Stop>
<Tab>
<TrebleDown>
<TrebleUp>
<UpArrow>
<VolumeDown>
<VolumeUp>
<WWWBack>
<WWWFavorites>
<WWWForward>
<WWWHome>
<WWWRefresh>
<WWWSearch>
<WWWStop>
<WakeUp>
<Yen>
<hankaku/zenkaku>
<henkan/zenkouho>
<hiragana/katakana>
<muhenkan>
